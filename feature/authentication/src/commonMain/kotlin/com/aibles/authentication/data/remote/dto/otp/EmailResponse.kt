package com.aibles.authentication.data.remote.dto.otp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmailResponse(
    @SerialName("status")
    val status: Int = 0,
    @SerialName("status_message")
    val statusMessage: String = "",
    @SerialName("timestamp")
    val timestamp: String = ""
)