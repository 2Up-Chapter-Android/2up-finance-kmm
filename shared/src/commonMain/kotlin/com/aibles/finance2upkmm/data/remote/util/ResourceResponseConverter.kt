package com.aibles.finance2upkmm.data.remote.util

import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.Converter
import de.jensklingenberg.ktorfit.internal.TypeData
import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.isSuccess


sealed class MyOwnResponse<T> {
    data class Success<T>(val data: T) : MyOwnResponse<T>()
    class Error(val ex:Throwable) : MyOwnResponse<Nothing>()

    companion object {
        fun <T> success(data: T) = Success(data)
        fun error(ex: Throwable) = Error(ex)
    }
}

//class ResourceResponseConverterFactory : Converter.Factory{
//
//    override fun suspendResponseConverter(
//        typeData: TypeData,
//        ktorfit: Ktorfit
//    ): Converter.SuspendResponseConverter<HttpResponse, *>? {
//        if(typeData.typeInfo.type == Resource::class) {
//
//            return object : Converter.SuspendResponseConverter<HttpResponse, Any> {
//                override suspend fun convert(response: HttpResponse): Any {
//                    return try {
//                        if (response.status.isSuccess()) {
//                            Resource.success(response.body(typeData.typeArgs.first().typeInfo))
//                        } else {
//                            val errorBody = response.body<BaseErrorResponse>().data
//                            Resource.error(CustomException(errorBody?.code, errorBody?.detail))
//                        }
//                    } catch (exception: Exception) {
//                        Resource.error(CustomException(errorMessage = exception.message))
//                    }
//                }
//            }
//        }
//        return null
//    }
//}
