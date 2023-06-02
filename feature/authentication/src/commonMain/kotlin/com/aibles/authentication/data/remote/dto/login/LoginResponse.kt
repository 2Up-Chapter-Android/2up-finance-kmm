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
        val access_token: String? = "nothing",
        val access_token_life_time: Int? = 0,
        val refresh_token: String? = "nothing",
        val refresh_token_life_time: Long? = 0,
        val token_type: String? = ""
    )
}