package com.aibles.transaction.presentation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.aibles.transaction.data.remote.dto.login.LoginTestRequest
import com.aibles.transaction.data.remote.services.TransactionDataSource
import com.aibles.transaction.data.remote.services.TransactionService
import de.jensklingenberg.ktorfit.Ktorfit
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject

class TransactionDashboardScreen: Screen, KoinComponent{
    @Composable
    override fun Content() {
        TransactionDashboardScreen()
    }

    @Composable
    private fun TransactionDashboardScreen(){
        val dataSource by inject<TransactionDataSource>()
        val text = remember { mutableStateOf("Hello Transaction") }
            GlobalScope.launch{
                text.value = dataSource.login(LoginTestRequest("Abcd!234", "a@yopmail.com")).data?.data?.access_token ?: "nothing bla bla bla"
            }
        Text(text.value, fontSize = 50.sp)
    }
}