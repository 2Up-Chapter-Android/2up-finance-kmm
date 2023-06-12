package com.aibles.authentication.domain.entity.otp

import kotlinx.serialization.Serializable

@Serializable
data class OTPRequest(
    val email: String = "",
    val otp: String = ""
)
