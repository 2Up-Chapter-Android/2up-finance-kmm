package com.aibles.authentication.presentation.ui.register

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.aibles.authentication.domain.entity.register.RegisterInfo
import com.aibles.authentication.domain.entity.register.RegisterRequest
import com.aibles.authentication.domain.usecase.RegisterUseCase
import com.aibles.finance2upkmm.data.remote.util.Resource
import com.aibles.finance2upkmm.presentation.until.isValidEmail
import com.aibles.finance2upkmm.presentation.until.isValidFullName
import com.aibles.finance2upkmm.presentation.until.isValidPassword
import com.aibles.finance2upkmm.presentation.until.isValidUsername
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RegisterViewModel : ScreenModel, KoinComponent {
    private val registerUseCase: RegisterUseCase by inject()


    private val _registerState = MutableStateFlow<Resource<RegisterInfo>>(Resource.loading())
    val registerState: StateFlow<Resource<RegisterInfo>> get() = _registerState

    private val _registerUiState = MutableStateFlow(RegisterUiState())
    val registerUiState: StateFlow<RegisterUiState> = _registerUiState.asStateFlow()

    fun registerRequest(
        invalidUsernameErrorMsg: String,
        invalidFullNameErrorMsg: String,
        invalidEmailErrorMsg: String,
        invalidPasswordErrorMsg: String,
        invalidConfirmPasswordErrorMsg: String
    ) {
        if (!isValidateInput(
                invalidUsernameErrorMsg,
                invalidFullNameErrorMsg,
                invalidEmailErrorMsg,
                invalidPasswordErrorMsg,
                invalidConfirmPasswordErrorMsg
            )
        ) return

        _registerUiState.value =
            registerUiState.value.copy(
                isLoading = true
            )

        coroutineScope.launch {
            delay(200)

            val registerResponse = registerUseCase(
                RegisterRequest(
                    username = registerUiState.value.usernameInput,
                    fullName = registerUiState.value.fullNameInput,
                    email = registerUiState.value.emailAddressInput,
                    password = registerUiState.value.passwordInput,
                    confirmPassword = registerUiState.value.confirmPasswordInput
                )
            )
            _registerUiState.value =
                registerUiState.value.copy(isLoading = registerResponse.isLoading())
            _registerState.tryEmit(registerResponse)

        }
    }

    private fun isValidateInput(
        invalidUsernameErrorMsg: String,
        invalidFullNameErrorMsg: String,
        invalidEmailErrorMsg: String,
        invalidPasswordErrorMsg: String,
        invalidConfirmPasswordErrorMsg: String
    ): Boolean {

        _registerUiState.value = registerUiState.value.copy(
            usernameError = "",
            fullNameError = "",
            passwordError = "",
            emailAddressError = "",
            passwordConfirmError = "",
        )
        var isValid = true
        if (!registerUiState.value.usernameInput.isValidUsername()) {
            _registerUiState.value = registerUiState.value.copy(
                usernameError = invalidUsernameErrorMsg
            )
            isValid = false
        }

        if (!registerUiState.value.fullNameInput.isValidFullName()) {
            _registerUiState.value = registerUiState.value.copy(
                fullNameError = invalidFullNameErrorMsg
            )
            isValid = false
        }

        if (!registerUiState.value.emailAddressInput.isValidEmail()) {
            _registerUiState.value = registerUiState.value.copy(
                emailAddressError = invalidEmailErrorMsg
            )
            isValid = false
        }

        if (!registerUiState.value.passwordInput.isValidPassword()) {
            _registerUiState.value = registerUiState.value.copy(
                passwordError = invalidPasswordErrorMsg
            )
            isValid = false
        }

        if (!registerUiState.value.confirmPasswordInput.isValidPassword()) {
            _registerUiState.value = registerUiState.value.copy(
                passwordConfirmError = invalidConfirmPasswordErrorMsg
            )
            isValid = false
        }
        return isValid
    }

    fun onUsernameValueChange(text: String) {
        _registerUiState.value = registerUiState.value.copy(usernameInput = text)
    }

    fun onFullNameValueChange(text: String) {
        _registerUiState.value = registerUiState.value.copy(fullNameInput = text)
    }

    fun onEmailAddressValueChange(text: String) {
        _registerUiState.value = registerUiState.value.copy(emailAddressInput = text)
    }

    fun onPasswordValueChange(text: String) {
        _registerUiState.value = registerUiState.value.copy(passwordInput = text)
    }

    fun onPasswordConfirmValueChange(text: String) {
        _registerUiState.value = registerUiState.value.copy(confirmPasswordInput = text)
    }
}