package com.aibles.authentication.domain.usecase

import com.aibles.authentication.domain.entity.otp.EmailRequest
import com.aibles.authentication.domain.repository.AuthenticationRepository

class SendEmailUseCase(private val repository: AuthenticationRepository) {
    suspend operator fun invoke(preOTPRequest: EmailRequest) = repository.sendEmail(preOTPRequest)
}