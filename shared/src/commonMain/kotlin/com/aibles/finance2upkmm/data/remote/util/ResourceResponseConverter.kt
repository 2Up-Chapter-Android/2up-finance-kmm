package com.aibles.finance2upkmm.data.remote.util

import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.Converter
import de.jensklingenberg.ktorfit.internal.TypeData
import io.ktor.client.call.*
import io.ktor.client.statement.*


sealed class MyOwnResponse<T> {
    data class Success<T>(val data: T?) : MyOwnResponse<T>()
    data class Error(val ex:CustomException) : MyOwnResponse<Nothing>()

    companion object {
        fun <T> success(data: T?) = Success(data)
        fun error(ex: CustomException) = Error(ex)
    }
}

fun <T> MyOwnResponse<T>.mapToResource(): Resource<T?>{
    return if (this is MyOwnResponse.Success) Resource.success(data)
    else Resource.error((this as MyOwnResponse.Error).ex)
}

class MyOwnResponseConverterFactory : Converter.Factory{

    override fun suspendResponseConverter(
        typeData: TypeData,
        ktorfit: Ktorfit
    ): Converter.SuspendResponseConverter<HttpResponse, *>? {
        if(typeData.typeInfo.type == MyOwnResponse::class) {

            return object : Converter.SuspendResponseConverter<HttpResponse, Any> {
                override suspend fun convert(response: HttpResponse): Any {
                    return try {
                        MyOwnResponse.success(response.body(typeData.typeArgs.first().typeInfo))
                    } catch (ex: Throwable) {
                        MyOwnResponse.error(CustomException(errorMessage = ex.message))
                    }
                }
            }
        }
        return null
    }
}

