package com.aibles.authentication.data.remote.service

import com.aibles.authentication.data.remote.dto.login.LoginRequest
import com.aibles.authentication.data.remote.dto.login.LoginResponse
import com.aibles.authentication.data.remote.dto.otp.OTPResponse
import com.aibles.authentication.data.remote.dto.otp.EmailResponse
import com.aibles.authentication.data.remote.dto.register.RegisterResponse
import com.aibles.authentication.domain.entity.otp.OTPRequest
import com.aibles.authentication.domain.entity.otp.EmailRequest
import com.aibles.authentication.domain.entity.register.RegisterRequest
import com.aibles.finance2upkmm.data.remote.util.Resource
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.POST

interface AuthenticationService {

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Resource<LoginResponse>

    @POST("auth/users/register")
    suspend fun register(@Body registerRequest: RegisterRequest): Resource<RegisterResponse>

    @POST("auth/otp/resend")
    suspend fun sendEmail(@Body preOTPRequest: EmailRequest): Resource<EmailResponse>

    @POST("auth/users/active")
    suspend fun sendOTP(@Body otpRequest: OTPRequest): Resource<OTPResponse>
}