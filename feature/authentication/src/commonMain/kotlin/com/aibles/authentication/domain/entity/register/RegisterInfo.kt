package com.aibles.authentication.domain.entity.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterInfo(
    val status: Int? = 0,
    val statusMessage: String? = "",
    val timestamp: String? = "",
    val data: AccountInformation? = null
) {
    @Serializable
    data class AccountInformation(
        val id: String? = "",
        val email: String? = "",
        val username: String? = "",
        val fullName: String? = "",
        val activated: String? = ""
    )
}