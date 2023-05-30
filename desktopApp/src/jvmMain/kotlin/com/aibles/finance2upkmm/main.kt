package com.aibles.finance2upkmm

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication
import com.aibles.finance2upkmm.presentation.util.GreetingView

fun main() =  singleWindowApplication (
    title = "Finance 2Up",
    state = WindowState(width = 1280.dp, height = 768.dp)
){
    GreetingView(Greeting().greet())
}