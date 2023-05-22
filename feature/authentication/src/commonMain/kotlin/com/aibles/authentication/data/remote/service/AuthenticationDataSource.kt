package com.aibles.authentication.data.remote.service

import com.aibles.authentication.data.remote.dto.login.LoginRequest
import com.aibles.authentication.domain.entity.otp.OTPRequest
import com.aibles.authentication.domain.entity.otp.EmailRequest
import com.aibles.authentication.domain.entity.register.RegisterRequest

class AuthenticationDataSource(private val service: AuthenticationService) {
    suspend fun login(loginRequest: LoginRequest) = service.login(loginRequest)
    suspend fun register(registerRequest: RegisterRequest) = service.register(registerRequest)
    suspend fun sendEmail(preOTPRequest: EmailRequest) = service.sendEmail(preOTPRequest)
    suspend fun sendOTP(otpRequest: OTPRequest) = service.sendOTP(otpRequest)
}