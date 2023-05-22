package com.aibles.authentication.domain.usecase

import com.aibles.authentication.domain.repository.AuthenticationRepository

class LoginUseCase(private val repository: AuthenticationRepository) {
    suspend operator fun invoke(username: String, password: String) = repository.login(username, password)
}