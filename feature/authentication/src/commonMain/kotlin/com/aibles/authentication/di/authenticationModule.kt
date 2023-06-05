package com.aibles.authentication.di

import cafe.adriel.voyager.core.registry.ScreenRegistry.register
import com.aibles.authentication.presentation.navigation.SharedScreen
import com.aibles.authentication.presentation.ui.login.LoginScreen
import com.aibles.authentication.presentation.ui.otp.OTPScreen
import com.aibles.authentication.presentation.ui.register.RegisterScreen
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.dsl.module

val authenticationModule = module {
    includes(
        authenticationDomainModule,
        authenticationNetworkModule,
    ).also {
        Napier.base(DebugAntilog())
    }
}

val navigationModule = module{
    register<SharedScreen.LoginScreen>{
        LoginScreen()
    }
    register<SharedScreen.RegisterScreen>{
        RegisterScreen()
    }
    register<SharedScreen.OTPScreen>{
        OTPScreen()
    }
}