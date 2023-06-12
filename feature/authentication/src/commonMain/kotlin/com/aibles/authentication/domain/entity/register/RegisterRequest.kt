package com.aibles.authentication.domain.entity.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val username: String? = "",
    val full_name: String? = "",
    val email: String? = "",
    val password: String? = "",
    val confirm_password: String? = "",
)