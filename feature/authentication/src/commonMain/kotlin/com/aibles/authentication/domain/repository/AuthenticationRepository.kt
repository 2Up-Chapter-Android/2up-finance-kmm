package com.aibles.authentication.domain.repository

import com.aibles.authentication.domain.entity.login.LoginResponseEntity
import com.aibles.authentication.domain.entity.otp.OTPInfo
import com.aibles.authentication.domain.entity.otp.OTPRequest
import com.aibles.authentication.domain.entity.otp.EmailInfo
import com.aibles.authentication.domain.entity.otp.EmailRequest
import com.aibles.authentication.domain.entity.register.RegisterInfo
import com.aibles.authentication.domain.entity.register.RegisterRequest
import com.aibles.finance2upkmm.data.remote.util.Resource

interface AuthenticationRepository {
    suspend fun login(username: String, password: String): Resource<LoginResponseEntity>

    suspend fun register(registerRequest: RegisterRequest): Resource<RegisterInfo>

    suspend fun sendEmail(preOTPRequest: EmailRequest): Resource<EmailInfo>

    suspend fun sendOTP(otpRequest: OTPRequest): Resource<OTPInfo>

}