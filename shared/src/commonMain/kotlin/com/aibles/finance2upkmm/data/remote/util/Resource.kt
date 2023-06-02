package com.aibles.finance2upkmm.data.remote.util

import de.jensklingenberg.ktorfit.internal.TypeData
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import io.ktor.util.reflect.TypeInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class Resource<out T>(val status: Status, val data: T?, val error: CustomException?) {
    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(error: CustomException, data: T? = null): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                error
            )
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(
                Status.LOADING,
                data,
                null
            )
        }
    }

    fun isSuccessful() = status == Status.SUCCESS

    fun isLoading() = status == Status.LOADING

    fun isError() = status == Status.ERROR
}

inline fun <X, Y> Resource<X>.map(transform: (X?) -> Y): Resource<Y> = Resource(status, transform(data), error)

suspend inline fun <reified T> HttpResponse.mapToResource(typeData: TypeData): Resource<T> {
    return withContext(Dispatchers.Default) {
        try {
            if (status.isSuccess()) {
                Resource.success(body(typeData.typeArgs.first().typeInfo))
            } else {
                val errorBody = body<BaseErrorResponse>().data
                Resource.error(CustomException(errorBody?.code, errorBody?.detail), null)
            }
        } catch (exception: Exception) {
            Resource.error(CustomException(errorMessage = exception.message), null)
        }
    }
}