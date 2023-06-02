package com.aibles.finance2upkmm.data.remote.util

import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.Converter
import de.jensklingenberg.ktorfit.internal.TypeData
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.statement.*
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.SerializationException

class ResourceResponseConverterFactory : Converter.Factory{

    override fun suspendResponseConverter(
        typeData: TypeData,
        ktorfit: Ktorfit
    ): Converter.SuspendResponseConverter<HttpResponse, *>? {
        if(typeData.typeInfo.type == Resource::class) {

            return object : Converter.SuspendResponseConverter<HttpResponse, Any> {
                override suspend fun convert(response: HttpResponse): Any {
                    return try {
                        response.mapToResource<Any>(typeData)
                    } catch (ex: ClientRequestException) {
                        Resource.error(CustomException(errorMessage = ex.message), null)
                    } catch (ex: IOException) {
                        Resource.error(CustomException(errorMessage = ex.message), null)
                    } catch (ex: SerializationException) {
                        Resource.error(CustomException(errorMessage = ex.message), null)
                    }
                }
            }
        }
        return null
    }
}

