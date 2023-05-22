package com.aibles.authentication.domain.entity.register

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    @SerialName("username")
    val username: String? = "",
    @SerialName("full_name")
    val fullName: String? = "",
    @SerialName("email")
    val email: String? = "",
    @SerialName("password")
    val password: String? = "",
    @SerialName("confirm_password")
    val confirmPassword: String? = "",
)