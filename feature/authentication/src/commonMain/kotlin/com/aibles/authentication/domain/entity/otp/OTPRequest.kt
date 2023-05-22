package com.aibles.authentication.domain.entity.otp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OTPRequest(
    @SerialName("email")
    val email: String = "",
    @SerialName("otp")
    val otp: String = ""
)
