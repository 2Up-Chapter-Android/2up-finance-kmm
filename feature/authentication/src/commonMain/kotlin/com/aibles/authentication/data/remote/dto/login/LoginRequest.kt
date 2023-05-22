package com.aibles.authentication.data.remote.dto.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    @SerialName("password")
    val password: String,
    @SerialName("username")
    val username: String
)