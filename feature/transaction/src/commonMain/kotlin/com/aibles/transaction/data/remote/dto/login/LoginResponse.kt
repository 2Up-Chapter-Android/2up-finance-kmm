package com.aibles.transaction.data.remote.dto.login


import com.aibles.finance2upkmm.data.local.SecureStorageConst
import kotlinx.serialization.Serializable

@Serializable
data class LoginTestResponse(
    val data: LoginTestResponseData?,
    val message: String? = "",
    val status: Int? = 0
) {
    @Serializable
    data class LoginTestResponseData(
        val access_token: String? = SecureStorageConst.DEFAULT_VALUE,
        val access_token_life_time: Int? = 0,
        val refresh_token: String? = SecureStorageConst.DEFAULT_VALUE,
        val refresh_token_life_time: Long? = 0,
        val token_type: String? = ""
    )
}