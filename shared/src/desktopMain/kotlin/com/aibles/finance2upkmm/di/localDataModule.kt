package com.aibles.finance2upkmm.di

import com.aibles.finance2upkmm.data.local.SecureStorageWrapper
import com.aibles.finance2upkmm.data.local.SecureStorageWrapperImpl
import org.koin.dsl.module

actual fun sharedLocalDataModule() = module {
    single<SecureStorageWrapper> { SecureStorageWrapperImpl() }
}