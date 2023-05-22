package com.aibles.authentication.presentation.ui.login

data class LoginUIState(
    val isLoading: Boolean = false,
    val usernameError: String = "",
    val passwordError: String = "",
    val isNullUsername: Boolean = true,
    val isNullPassword: Boolean = true,
){
    val enableLoginButton get() = !isLoading && !isNullUsername && !isNullPassword
    val visibilityUsernameError get() = usernameError.isNotEmpty()
    val visibilityPasswordError get() = passwordError.isNotEmpty()
}
