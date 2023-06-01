package com.aibles.authentication.data.remote.dto.login


import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val data: LoginResponseData?,
    val message: String? = "",
    val status: Int? = 0
) {
    @Serializable
    data class LoginResponseData(
        val accessToken: String? = "nothing",
        val accessTokenLifeTime: Int? = 0,
        val refreshToken: String? = "nothing",
        val refreshTokenLifeTime: Long? = 0,
        val tokenType: String? = ""
    )
}