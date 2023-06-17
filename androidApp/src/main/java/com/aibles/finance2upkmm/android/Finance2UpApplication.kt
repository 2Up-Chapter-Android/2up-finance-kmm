package com.aibles.finance2upkmm.android

import android.app.Application
import com.aibles.authentication.di.authenticationModule
import com.aibles.finance2upkmm.di.sharedModule
import com.aibles.transaction.di.transactionModule
import com.orhanobut.hawk.Hawk
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Finance2UpApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build()
        startKoin {
            androidLogger(level = if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(androidContext = this@Finance2UpApplication)
            modules(
                listOf(
                    sharedModule,
                    authenticationModule,
                    transactionModule
                )
            )
        }
    }
}