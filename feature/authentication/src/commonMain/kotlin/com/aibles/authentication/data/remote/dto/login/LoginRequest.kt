package com.aibles.authentication.data.remote.dto.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val password: String,
    val email: String
)