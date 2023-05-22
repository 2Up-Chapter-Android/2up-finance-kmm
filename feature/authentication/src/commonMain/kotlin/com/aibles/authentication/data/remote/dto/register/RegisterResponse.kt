package com.aibles.authentication.data.remote.dto.register

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    @SerialName("status")
    val status: Int? = 0,
    @SerialName("status_message")
    val statusMessage: String? = "",
    @SerialName("timestamp")
    val timestamp: String? = "",
    @SerialName("data")
    val data: Data?
) {
    @Serializable
    data class Data(
        @SerialName("id")
        val id: String? = "",
        @SerialName("email")
        val email: String? = "",
        @SerialName("username")
        val username: String? = "",
        @SerialName("full_name")
        val fullName: String? = "",
        @SerialName("activated")
        val activated: String? = "",
    )
}


