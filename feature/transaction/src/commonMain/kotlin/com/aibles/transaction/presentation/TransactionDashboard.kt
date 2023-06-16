package com.aibles.transaction.presentation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen

class TransactionDashboardScreen: Screen{
    @Composable
    override fun Content() {
        TransactionDashboardScreen()
    }

    @Composable
    private fun TransactionDashboardScreen(){
        Text("Hello Transaction", fontSize = 50.sp)
    }
}