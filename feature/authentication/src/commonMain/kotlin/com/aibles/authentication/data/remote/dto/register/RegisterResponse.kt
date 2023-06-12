package com.aibles.authentication.data.remote.dto.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val status: Int? = 0,
    val status_message: String? = "",
    val timestamp: String? = "",
    val data: Data?
) {
    @Serializable
    data class Data(
        val id: String? = "",
        val email: String? = "",
        val username: String? = "",
        val full_name: String? = "",
        val activated: String? = "",
    )
}


