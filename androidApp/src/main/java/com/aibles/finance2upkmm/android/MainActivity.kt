package com.aibles.finance2upkmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.navigator.Navigator
import com.aibles.authentication.presentation.ui.login.LoginScreen
import com.aibles.authentication.presentation.ui.otp.OTPScreen
import com.aibles.authentication.presentation.ui.register.RegisterScreen
import com.aibles.finance2upkmm.Greeting

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigator(screen = LoginScreen())
//                    MainView()
                }
            }
        }
    }
}
