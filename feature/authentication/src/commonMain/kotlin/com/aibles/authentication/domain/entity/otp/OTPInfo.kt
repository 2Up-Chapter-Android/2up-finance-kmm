package com.aibles.authentication.domain.entity.otp

import kotlinx.serialization.Serializable

@Serializable
data class OTPInfo(
    val status: Int = 0,
    val statusMessage: String = "",
    val timestamp: String = ""
)