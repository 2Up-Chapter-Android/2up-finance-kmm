package com.aibles.authentication.di

import com.aibles.authentication.data.repository.AuthenticationRepositoryImpl
import com.aibles.authentication.domain.repository.AuthenticationRepository
import com.aibles.authentication.domain.usecase.LoginUseCase
import com.aibles.authentication.domain.usecase.RegisterUseCase
import com.aibles.authentication.domain.usecase.SendEmailUseCase
import com.aibles.authentication.domain.usecase.SendOTPUseCase
import org.koin.dsl.module

val authenticationDomainModule = module {
    single<AuthenticationRepository> { AuthenticationRepositoryImpl(get(), get()) }
    factory { LoginUseCase(get()) }
    factory { RegisterUseCase(get()) }
    factory { SendEmailUseCase(get()) }
    factory { SendOTPUseCase(get()) }
}