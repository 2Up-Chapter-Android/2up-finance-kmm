package com.aibles.authentication.presentation.ui.register

data class RegisterUiState(

    val isLoading: Boolean = false,

    val usernameError: String = "",
    val fullNameError: String = "",
    val emailAddressError: String = "",
    val passwordError: String = "",
    val passwordConfirmError: String = "",

    val usernameInput: String = "",
    val fullNameInput: String = "",
    val emailAddressInput: String = "",
    val passwordInput: String = "",
    val confirmPasswordInput: String = "",
) {
    val enableRegisterButton
        get() = !isLoading &&
                usernameInput.isNotBlank() &&
                fullNameInput.isNotBlank() &&
                emailAddressInput.isNotBlank() &&
                passwordInput.isNotBlank() &&
                confirmPasswordInput.isNotBlank()

    val visibilityUsernameError get() = usernameError.isNotBlank()
    val visibilityFullNameError get() = fullNameError.isNotBlank()
    val visibilityEmailAddressError get() = emailAddressError.isNotBlank()
    val visibilityPasswordError get() = passwordError.isNotBlank()
    val visibilityConfirmPasswordError get() = passwordConfirmError.isNotBlank()
}