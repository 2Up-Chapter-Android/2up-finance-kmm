package com.aibles.authentication.data.repository

import com.aibles.authentication.data.remote.service.AuthenticationDataSource
import com.aibles.authentication.domain.entity.login.LoginResponseEntity
import com.aibles.authentication.domain.entity.otp.EmailInfo
import com.aibles.authentication.domain.entity.otp.EmailRequest
import com.aibles.authentication.domain.entity.otp.OTPInfo
import com.aibles.authentication.domain.entity.otp.OTPRequest
import com.aibles.authentication.domain.entity.register.RegisterInfo
import com.aibles.authentication.domain.entity.register.RegisterRequest
import com.aibles.authentication.domain.repository.AuthenticationRepository
import com.aibles.finance2upkmm.data.remote.util.Resource
import com.aibles.finance2upkmm.data.remote.util.map
import com.aibles.authentication.data.mapping.mapToDomain
import com.aibles.authentication.data.remote.dto.login.LoginRequest

class AuthenticationRepositoryImpl(
    private val dataSource: AuthenticationDataSource,
) : AuthenticationRepository {

    override suspend fun login(username: String, password: String): Resource<LoginResponseEntity> {
        val loginRequest = LoginRequest(password, username)
        val loginResponse: Resource<LoginResponseEntity> =
            dataSource.login(loginRequest).map { it.mapToDomain() }
        if (loginResponse.isSuccessful()) {
//                HawkDataSource.saveAccessToken(
//                    loginResponse.data?.data?.accessToken ?: HawkDataSource.HawkConst.DEFAULT_VALUE
//                )
//                HawkDataSource.saveRefreshToken(
//                    loginResponse.data?.data?.refreshToken ?: HawkDataSource.HawkConst.DEFAULT_VALUE
//                )
        }
        return loginResponse
    }

    override suspend fun register(registerRequest: RegisterRequest): Resource<RegisterInfo> {

        return dataSource.register(registerRequest).map {
            it.mapToDomain()
        }
    }

    override suspend fun sendEmail(preOTPRequest: EmailRequest): Resource<EmailInfo> {

        return dataSource.sendEmail(preOTPRequest)
            .map {
                it.mapToDomain()
            }
    }

    override suspend fun sendOTP(otpRequest: OTPRequest): Resource<OTPInfo> {

        return dataSource.sendOTP(otpRequest)
            .map {
                it.mapToDomain()
            }
    }

}