package com.aibles.authentication.di

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.dsl.module

val authenticationModule = module {
    includes(
        authenticationDomainModule,
        authenticationNetworkModule
    ).also {
        Napier.base(DebugAntilog())
    }
}