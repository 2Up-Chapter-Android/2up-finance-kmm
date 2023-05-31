package com.aibles.authentication.di

import com.aibles.authentication.data.remote.service.AuthenticationDataSource
import com.aibles.authentication.data.remote.service.AuthenticationService
import de.jensklingenberg.ktorfit.Ktorfit
import kotlinx.serialization.serializer
import org.koin.dsl.module

val authenticationNetworkModule = module {
    single<AuthenticationService> { provideService(get()) }
    single { AuthenticationDataSource(get()) }
}

private fun provideService(ktorfit: Ktorfit) = ktorfit.create<AuthenticationService>()