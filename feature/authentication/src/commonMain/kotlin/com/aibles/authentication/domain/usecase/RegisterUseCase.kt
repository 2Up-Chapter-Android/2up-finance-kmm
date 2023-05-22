package com.aibles.authentication.domain.usecase

import com.aibles.authentication.domain.entity.register.RegisterRequest
import com.aibles.authentication.domain.repository.AuthenticationRepository

class RegisterUseCase(private val repository: AuthenticationRepository) {
    suspend operator fun invoke(registerRequest: RegisterRequest) = repository.register(registerRequest)
}