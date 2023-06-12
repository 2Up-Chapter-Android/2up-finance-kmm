package com.aibles.authentication.data.remote.dto.otp

import kotlinx.serialization.Serializable

@Serializable
data class OTPResponse(
    val status: Int = 0,
    val status_message: String = "",
    val timestamp: String = ""
)