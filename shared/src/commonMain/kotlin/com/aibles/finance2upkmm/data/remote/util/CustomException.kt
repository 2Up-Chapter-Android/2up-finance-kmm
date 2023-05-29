package com.aibles.finance2upkmm.data.remote.util

data class CustomException(
    val errorCode: String? = "",
    val errorMessage: String? = ""
): Exception(errorMessage)