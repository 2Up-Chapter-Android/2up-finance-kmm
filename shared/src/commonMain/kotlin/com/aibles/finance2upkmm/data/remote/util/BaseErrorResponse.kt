package com.aibles.finance2upkmm.data.remote.util

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseErrorResponse(
    @SerialName("status") val status: Int? = 0,
    @SerialName("status_message") val statusMessage: String? = "",
    @SerialName("timestamp") val timestamp: String? = "",
    @SerialName("data") val data: Data?,
){
    @Serializable
    data class Data(
        @SerialName("code") val code: String? = "",
        @SerialName("detail") val detail: String? = "",
    )
}
