package com.aibles.finance2upkmm.data.remote.util

data class CustomException(
    val statusCode: String? = "",
    val errorMessage: String? = ""
): Exception(errorMessage)