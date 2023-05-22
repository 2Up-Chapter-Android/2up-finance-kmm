package com.aibles.authentication.data.remote.dto.login


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("data")
    val data: LoginResponseData?,
    @SerialName("message")
    val message: String? = "",
    @SerialName("status")
    val status: Int? = 0
) {
    @Serializable
    data class LoginResponseData(
        @SerialName("access_token")
        val accessToken: String? = "nothing",
        @SerialName("access_token_life_time")
        val accessTokenLifeTime: Int? = 0,
        @SerialName("refresh_token")
        val refreshToken: String? = "nothing",
        @SerialName("refresh_token_life_time")
        val refreshTokenLifeTime: Long? = 0,
        @SerialName("token_type")
        val tokenType: String? = ""
    )
}