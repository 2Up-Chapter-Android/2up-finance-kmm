package com.finance2up.authentication.presentation.ui.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aibles.finance.data.remote.util.Resource
import com.aibles.finance.presentation.utils.ResourcesProvider
import com.aibles.finance.utils.*
import com.finance2up.authentication.R
import com.finance2up.authentication.domain.entity.register.RegisterInfo
import com.finance2up.authentication.domain.entity.register.RegisterRequest
import com.finance2up.authentication.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private var registerUseCase: RegisterUseCase,
    private val resourcesProvider: ResourcesProvider,
) :
    ViewModel() {

    private val _registerState = MutableStateFlow<Resource<RegisterInfo>>(Resource.loading())
    val registerState: StateFlow<Resource<RegisterInfo>> get() = _registerState

    private val _registerUiState = MutableStateFlow(RegisterUiState())
    val registerUiState: StateFlow<RegisterUiState>  = _registerUiState.asStateFlow()


    fun registerRequest() {

        if (!isValidateInput()) return

        _registerUiState.value =
            registerUiState.value.copy(
                isLoading = true
            )

        viewModelScope.launch(Dispatchers.Main) {

            delay(200)

            val registerResponse = registerUseCase(
                RegisterRequest(
                    username = registerUiState.value.usernameInput,
                    fullName = registerUiState.value.fullNameInput,
                    email =  registerUiState.value.emailAddressInput,
                    password = registerUiState.value.passwordInput,
                    confirmPassword = registerUiState.value.confirmPasswordInput

                )
            )
            _registerUiState.value =
                registerUiState.value.copy(isLoading = registerResponse.isLoading())
            _registerState.tryEmit(registerResponse)

            Log.d("check_response", "--- $registerResponse")
        }
    }

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
                usernameError = resourcesProvider.getString(
                    R.string.register_error_invalid_username
                )
            )
            isValid = false
        }

        if (!registerUiState.value.fullNameInput.isValidFullName()) {
            _registerUiState.value =
                registerUiState.value.copy(
                    fullNameError = resourcesProvider.getString(R.string.register_error_invalid_full_name)
                )
            isValid = false
        }

        if (!registerUiState.value.emailAddressInput.isValidEmail()) {
            _registerUiState.value = registerUiState.value.copy(
                emailAddressError = resourcesProvider.getString(
                    R.string.register_error_invalid_email
                )
            )
            isValid = false
        }

        if (!registerUiState.value.passwordInput.isValidPassword()) {
            _registerUiState.value = registerUiState.value.copy(
                passwordError = resourcesProvider.getString(
                    R.string.register_error_invalid_password
                )
            )
            isValid = false
        }

        if (!registerUiState.value.confirmPasswordInput.isValidPassword()) {
            _registerUiState.value = registerUiState.value.copy(
                passwordConfirmError = resourcesProvider.getString(
                    R.string.register_error_invalid_confirm_password
                )
            )
            isValid = false
        }
        return isValid
    }

    fun onUsernameValueChange(text: String) {
        _registerUiState.value = registerUiState.value.copy(
            usernameInput = text,
        )
    }

    fun onFullNameValueChange(text: String) {
        _registerUiState.value = registerUiState.value.copy(
            fullNameInput = text,
        )
    }

    fun onEmailAddressValueChange(text: String) {
        _registerUiState.value = registerUiState.value.copy(
            emailAddressInput = text,
        )
    }

    fun onPasswordValueChange(text: String) {
        _registerUiState.value = registerUiState.value.copy(
            passwordInput = text,
        )
    }

    fun onPasswordConfirmValueChange(text: String) {
        _registerUiState.value = registerUiState.value.copy(
            confirmPasswordInput = text,
        )
    }
}