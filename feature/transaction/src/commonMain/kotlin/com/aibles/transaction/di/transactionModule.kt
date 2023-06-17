package com.aibles.transaction.di

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.dsl.module

val transactionModule = module {
    includes(
        transactionDomainModule,
        transactionNavigationModule,
        transactionNetworkModule
    ).also {
        Napier.base(DebugAntilog())
    }
}