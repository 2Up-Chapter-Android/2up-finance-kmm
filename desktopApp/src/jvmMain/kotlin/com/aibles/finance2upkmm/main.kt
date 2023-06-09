package com.aibles.finance2upkmm

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication
import cafe.adriel.voyager.navigator.Navigator
import com.aibles.authentication.di.authenticationModule
import com.aibles.authentication.presentation.ui.login.LoginScreen
import com.aibles.finance2upkmm.di.sharedModule
import com.aibles.transaction.di.transactionNetworkModule
import com.aibles.transaction.presentation.TransactionDashboardScreen
import org.koin.core.context.KoinContextHandler
import org.koin.core.context.startKoin

fun main() =  singleWindowApplication (
    title = "Finance 2Up",
    state = WindowState(width = 1280.dp, height = 768.dp)
){
    if (KoinContextHandler.getOrNull() == null) {
        startKoin {
            modules(
                listOf(
                    sharedModule,
                    authenticationModule,
                    transactionNetworkModule
                )
            )
        }
    }
    val koin = KoinContextHandler.get()
    Navigator(screen = TransactionDashboardScreen())
}