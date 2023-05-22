package com.aibles.authentication.domain.entity.login

import kotlinx.serialization.Serializable


@Serializable
data class LoginResponseEntity(
    val data: LoginResponseData? = null,
    val message: String? = "",
    val status: Int? = 0
){
    @Serializable
    data class LoginResponseData(
        val accessToken: String? = "nothing",
        val accessTokenLifeTime: Int? = 0,
        val refreshToken: String? = "nothing",
        val refreshTokenLifeTime: Long? = 0,
        val tokenType: String? = ""
    )
}
