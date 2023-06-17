package com.aibles.transaction.data.remote.dto.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginTestRequest(
    val password: String,
    val email: String
)