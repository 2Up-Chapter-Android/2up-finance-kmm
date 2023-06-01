package com.aibles.authentication.data.mapping

import com.aibles.authentication.data.remote.dto.login.LoginResponse
import com.aibles.authentication.data.remote.dto.otp.OTPResponse
import com.aibles.authentication.data.remote.dto.otp.EmailResponse
import com.aibles.authentication.data.remote.dto.register.RegisterResponse
import com.aibles.authentication.domain.entity.login.LoginResponseEntity
import com.aibles.authentication.domain.entity.otp.OTPInfo
import com.aibles.authentication.domain.entity.otp.EmailInfo
import com.aibles.authentication.domain.entity.register.RegisterInfo

fun RegisterResponse?.mapToDomain(): RegisterInfo {
    return this?.let {
        RegisterInfo(
            status = status,
            statusMessage = statusMessage,
            timestamp = timestamp,
            data = data.mapToDomain()
        )
    } ?: RegisterInfo()
}

fun RegisterResponse.Data?.mapToDomain(): RegisterInfo.AccountInformation {
    return this?.let {
        RegisterInfo.AccountInformation(
            id = id,
            email = email,
            username = username,
            fullName = fullName,
            activated = activated
        )
    } ?: RegisterInfo.AccountInformation()
}

fun LoginResponse?.mapToDomain(): LoginResponseEntity {
//    return this?.let {
//        LoginResponseEntity(
//            data = data.mapToDomain(),
//            message = message,
//            status = status
//        )
//    } ?: LoginResponseEntity()
    return LoginResponseEntity(
        this?.data.mapToDomain(),
        this?.message ?: "",
        this?.status ?: 0
    )
}

private fun LoginResponse.LoginResponseData?.mapToDomain(): LoginResponseEntity.LoginResponseData {
//    return this?.let {
//        LoginResponseEntity.LoginResponseData(
//            accessToken = accessToken,
//            accessTokenLifeTime = accessTokenLifeTime,
//            refreshToken = refreshToken,
//            refreshTokenLifeTime = refreshTokenLifeTime,
//            tokenType = tokenType
//        )
//    } ?: LoginResponseEntity.LoginResponseData()
    return LoginResponseEntity.LoginResponseData(
        this?.accessToken ?: "nothing",
        this?.accessTokenLifeTime ?: 0,
        this?.refreshToken ?: "nothing",
        this?.refreshTokenLifeTime ?: 0,
        this?.tokenType ?: ""
    )
}

fun EmailResponse?.mapToDomain(): EmailInfo {
    return this?.let {
        EmailInfo(
            status = status,
            statusMessage = statusMessage,
            timestamp = timestamp,
        )
    } ?: EmailInfo()
}


fun OTPResponse?.mapToDomain(): OTPInfo {
    return this?.let {
        OTPInfo(
            status = status,
            statusMessage = statusMessage,
            timestamp = timestamp,
        )
    } ?: OTPInfo()
}
