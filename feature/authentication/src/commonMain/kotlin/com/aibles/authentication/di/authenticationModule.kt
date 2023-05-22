package com.aibles.authentication.di

import org.koin.dsl.module

val authenticationModule = module {
    includes(
        authenticationDomainModule,
        authenticationNetworkModule
    )
}