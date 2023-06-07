package com.aibles.authentication.presentation.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed class SharedScreen : ScreenProvider{
    object LoginScreen : SharedScreen()
    object RegisterScreen : SharedScreen()
    object OTPScreen : SharedScreen()
}
