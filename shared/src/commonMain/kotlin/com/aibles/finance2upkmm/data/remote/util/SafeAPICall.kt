package com.aibles.finance2upkmm.data.remote.util

import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.SerializationException

inline fun <reified T> safeApiCall(
    apiCall: () -> Resource<T>,
): Resource<T> = try {
    apiCall()
} catch (e: IOException) {
    Resource.error(NetworkException, null)
} catch (e: SerializationException) {
    Resource.error(SerializationError, null)
}