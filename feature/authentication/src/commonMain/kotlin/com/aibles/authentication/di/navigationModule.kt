package com.aibles.authentication.di

import cafe.adriel.voyager.core.registry.ScreenRegistry
import com.aibles.authentication.presentation.navigation.SharedScreen
import com.aibles.authentication.presentation.ui.login.LoginScreen
import com.aibles.authentication.presentation.ui.otp.OTPScreen
import com.aibles.authentication.presentation.ui.register.RegisterScreen
import org.koin.dsl.module

val authenticationNavigationModule = module{
    ScreenRegistry.register<SharedScreen.LoginScreen>{
        LoginScreen()
    }
    ScreenRegistry.register<SharedScreen.RegisterScreen>{
        RegisterScreen()
    }
    ScreenRegistry.register<SharedScreen.OTPScreen>{
        OTPScreen()
    }
}