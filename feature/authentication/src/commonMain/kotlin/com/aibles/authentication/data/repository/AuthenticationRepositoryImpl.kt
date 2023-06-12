package com.aibles.authentication.data.repository

import com.aibles.authentication.data.mapping.mapToDomain
import com.aibles.authentication.data.remote.dto.login.LoginRequest
import com.aibles.authentication.data.remote.service.AuthenticationDataSource
import com.aibles.authentication.domain.entity.login.LoginResponseEntity
import com.aibles.authentication.domain.entity.otp.EmailInfo
import com.aibles.authentication.domain.entity.otp.EmailRequest
import com.aibles.authentication.domain.entity.otp.OTPInfo
import com.aibles.authentication.domain.entity.otp.OTPRequest
import com.aibles.authentication.domain.entity.register.RegisterInfo
import com.aibles.authentication.domain.entity.register.RegisterRequest
import com.aibles.authentication.domain.repository.AuthenticationRepository
import com.aibles.finance2upkmm.data.local.SecureStorageConst
import com.aibles.finance2upkmm.data.local.SecureStorageKey
import com.aibles.finance2upkmm.data.local.SecureStorageWrapper
import com.aibles.finance2upkmm.data.remote.util.Resource
import com.aibles.finance2upkmm.data.remote.util.map
import com.aibles.finance2upkmm.data.remote.util.safeApiCall

class AuthenticationRepositoryImpl(
    private val dataSource: AuthenticationDataSource,
    private val secureStorageWrapperImpl: SecureStorageWrapper
) : AuthenticationRepository {

    override suspend fun login(username: String, password: String): Resource<LoginResponseEntity> {
        val loginRequest = LoginRequest(password, username)
        val loginResponse = safeApiCall { dataSource.login(loginRequest) }.map { it.mapToDomain() }
        if (loginResponse.isSuccessful()) {
                secureStorageWrapperImpl.saveValue(
                    SecureStorageKey.ACCESS_TOKEN,
                    loginResponse.data?.data?.accessToken ?: SecureStorageConst.DEFAULT_VALUE
                )
            secureStorageWrapperImpl.saveValue(
                SecureStorageKey.REFRESH_TOKEN,
                loginResponse.data?.data?.refreshToken ?: SecureStorageConst.DEFAULT_VALUE
                )
        }
        return loginResponse
    }

    override suspend fun register(registerRequest: RegisterRequest): Resource<RegisterInfo> {

        return safeApiCall { dataSource.register(registerRequest) }
            .map {
                it.mapToDomain()
            }
    }

    override suspend fun sendEmail(preOTPRequest: EmailRequest): Resource<EmailInfo> {

        return safeApiCall { dataSource.sendEmail(preOTPRequest) }
            .map {
                it.mapToDomain()
            }
    }

    override suspend fun sendOTP(otpRequest: OTPRequest): Resource<OTPInfo> {

        return safeApiCall { dataSource.sendOTP(otpRequest) }
            .map {
                it.mapToDomain()
            }
    }

}