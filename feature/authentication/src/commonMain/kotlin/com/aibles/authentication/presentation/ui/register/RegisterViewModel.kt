package com.aibles.authentication.presentation.ui.register

import Finance2upKMM.feature.authentication.MR
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.aibles.authentication.domain.entity.register.RegisterInfo
import com.aibles.authentication.domain.entity.register.RegisterRequest
import com.aibles.authentication.domain.usecase.RegisterUseCase
import com.aibles.finance2upkmm.data.remote.util.Resource
import com.aibles.finance2upkmm.presentation.util.isValidEmail
import com.aibles.finance2upkmm.presentation.util.isValidFullName
import com.aibles.finance2upkmm.presentation.util.isValidPassword
import com.aibles.finance2upkmm.presentation.util.isValidUsername
import dev.icerock.moko.resources.compose.localized
import dev.icerock.moko.resources.desc.desc
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

    @Composable
    fun registerRequest() {
        if (!isValidateInput()) return

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

    @Composable
    private fun isValidateInput(): Boolean {

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
                usernameError = MR.strings.register_error_invalid_username.desc().localized()
            )
            isValid = false
        }

        if (!registerUiState.value.fullNameInput.isValidFullName()) {
            _registerUiState.value = registerUiState.value.copy(
                fullNameError = MR.strings.register_error_invalid_full_name.desc().localized()
            )
            isValid = false
        }

        if (!registerUiState.value.emailAddressInput.isValidEmail()) {
            _registerUiState.value = registerUiState.value.copy(
                emailAddressError = MR.strings.register_error_invalid_email.desc().localized()
            )
            isValid = false
        }

        if (!registerUiState.value.passwordInput.isValidPassword()) {
            _registerUiState.value = registerUiState.value.copy(
                passwordError = MR.strings.register_error_invalid_password.desc().localized()
            )
            isValid = false
        }

        if (!registerUiState.value.confirmPasswordInput.isValidPassword()) {
            _registerUiState.value = registerUiState.value.copy(
                passwordConfirmError = MR.strings.register_error_invalid_confirm_password.desc()
                    .localized()
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