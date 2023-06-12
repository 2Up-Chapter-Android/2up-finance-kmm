package com.aibles.authentication.domain.entity.otp

import kotlinx.serialization.Serializable

@Serializable
data class EmailRequest(
    val email: String = ""
)
