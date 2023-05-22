package com.aibles.authentication.domain.usecase

import com.aibles.authentication.domain.entity.otp.OTPRequest
import com.aibles.authentication.domain.repository.AuthenticationRepository

class SendOTPUseCase(private val repository: AuthenticationRepository) {
    suspend operator fun invoke(otpRequest: OTPRequest) = repository.sendOTP(otpRequest)
}