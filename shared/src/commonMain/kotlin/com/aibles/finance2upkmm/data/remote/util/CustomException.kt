package com.aibles.finance2upkmm.data.remote.util

data class HttpException(val code: String? = "", val msg: String? = ""): CustomException(code, msg)

object NetworkException: CustomException()

object SerializationError : CustomException()

data class UnknownException(val msg: String? = ""): CustomException(msg)

sealed class CustomException(val errorCode: String? = "", val errorMessage: String? = "") : Exception(errorMessage)