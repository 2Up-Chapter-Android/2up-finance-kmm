package com.aibles.authentication.presentation.ui.login

import Finance2upKMM.feature.authentication.MR
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.aibles.authentication.domain.entity.login.LoginResponseEntity
import com.aibles.authentication.domain.usecase.LoginUseCase
import com.aibles.finance2upkmm.data.remote.util.Resource
import com.aibles.finance2upkmm.presentation.util.isValidPassword
import com.aibles.finance2upkmm.presentation.util.isValidUsername
import dev.icerock.moko.resources.compose.localized
import dev.icerock.moko.resources.desc.desc
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginViewModel: ScreenModel, KoinComponent {
    private val loginUseCase: LoginUseCase by inject()

    private val _usernameInput = MutableStateFlow("")
    val usernameInput: StateFlow<String> get() = _usernameInput

    private val _passwordInput = MutableStateFlow("")
    val passwordInput: StateFlow<String> get() = _passwordInput

    private val _loginState = MutableStateFlow<Resource<LoginResponseEntity>>(Resource.loading())
    val loginState: StateFlow<Resource<LoginResponseEntity>> get() = _loginState

    private val _loginUiState = MutableStateFlow(LoginUIState())
    val loginUiState: StateFlow<LoginUIState> get() = _loginUiState

    @Composable
    fun login() {
        if (!validateInput()) return
        _loginUiState.value = loginUiState.value.copy(isLoading = true)
        coroutineScope.launch {
            delay(200)
            val loginResponse = loginUseCase(usernameInput.value, passwordInput.value)
            _loginUiState.value = loginUiState.value.copy(isLoading = loginResponse.isLoading())
            _loginState.tryEmit(loginResponse)

        }
    }

    @Composable
    private fun validateInput(): Boolean {
        _loginUiState.value = loginUiState.value.copy(usernameError = "", passwordError = "")
        var isValid = true
        if (!usernameInput.value.isValidUsername()) {
            _loginUiState.value =
                loginUiState.value.copy(usernameError = MR.strings.login_error_notExistAccount.desc().localized())
            isValid = false
        }

        if (!passwordInput.value.isValidPassword()) {
            _loginUiState.value =
                loginUiState.value.copy(passwordError = MR.strings.login_error_incorrectPassword.desc().localized())
            isValid = false
        }
        return isValid
    }

    fun onUsernameValueChange(text: String) {
        _usernameInput.value = text
        _loginUiState.value = loginUiState.value.copy(isNullUsername = text.isBlank())
    }

    fun onPasswordValueChange(text: String) {
        _passwordInput.value = text
        _loginUiState.value = loginUiState.value.copy(isNullPassword = text.isBlank())
    }
}