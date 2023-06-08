package com.aibles.finance2upkmm

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication
import cafe.adriel.voyager.navigator.Navigator
import com.aibles.authentication.presentation.ui.login.LoginScreen

fun main() =  singleWindowApplication (
    title = "Finance 2Up",
    state = WindowState(width = 1280.dp, height = 768.dp)
){
    Navigator(screen = LoginScreen())
}