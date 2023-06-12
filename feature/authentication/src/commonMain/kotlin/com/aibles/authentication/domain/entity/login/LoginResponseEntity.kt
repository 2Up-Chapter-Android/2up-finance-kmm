package com.aibles.authentication.domain.entity.login

import com.aibles.finance2upkmm.data.local.SecureStorageConst
import kotlinx.serialization.Serializable


@Serializable
data class LoginResponseEntity(
    val data: LoginResponseData? = null,
    val message: String? = "",
    val status: Int? = 0
){
    @Serializable
    data class LoginResponseData(
        val accessToken: String? = SecureStorageConst.DEFAULT_VALUE,
        val accessTokenLifeTime: Int? = 0,
        val refreshToken: String? = SecureStorageConst.DEFAULT_VALUE,
        val refreshTokenLifeTime: Long? = 0,
        val tokenType: String? = ""
    )
}
