package com.aibles.finance2upkmm.di

import org.koin.dsl.module

val sharedModule = module {
    includes(
        networkModule,
        sharedLocalDataModule()
    )
}