package com.aibles.authentication.di

import com.aibles.authentication.data.remote.service.AuthenticationDataSource
import com.aibles.authentication.data.remote.service.AuthenticationService
import de.jensklingenberg.ktorfit.Ktorfit
import org.koin.dsl.module

val authenticationNetworkModule = module {
    single<AuthenticationService> { get<Ktorfit>().create() }
    single { AuthenticationDataSource(get()) }
}