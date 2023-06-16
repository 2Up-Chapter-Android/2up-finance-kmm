package com.aibles.transaction.presentation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.aibles.transaction.data.remote.services.TransactionService
import de.jensklingenberg.ktorfit.Ktorfit
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class TransactionDashboardScreen: Screen, KoinComponent{
    @Composable
    override fun Content() {
        TransactionDashboardScreen()
    }

    @Composable
    private fun TransactionDashboardScreen(){
        Text("Hello Transaction", fontSize = 50.sp)
    }
}