package com.aibles.finance2upkmm.android

import android.app.Application
import com.aibles.authentication.di.authenticationModule
import com.aibles.authentication.di.navigationModule
import com.aibles.finance2upkmm.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Finance2UpApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(level = if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(androidContext = this@Finance2UpApplication)
            modules(
                listOf(
                    networkModule,
                    authenticationModule,
                    navigationModule
                )
            )
        }
    }
}