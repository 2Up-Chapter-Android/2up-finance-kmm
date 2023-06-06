package com.aibles.authentication.presentation.ui.otp

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.aibles.authentication.domain.entity.otp.EmailInfo
import com.aibles.authentication.domain.entity.otp.EmailRequest
import com.aibles.authentication.domain.entity.otp.OTPInfo
import com.aibles.authentication.domain.entity.otp.OTPRequest
import com.aibles.authentication.domain.usecase.SendEmailUseCase
import com.aibles.authentication.domain.usecase.SendOTPUseCase
import com.aibles.finance2upkmm.data.remote.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class OTPViewModel: ScreenModel, KoinComponent {
    private val sendEmailUsecase: SendEmailUseCase by inject()
    private val sendOTPUseCase: SendOTPUseCase by inject()

    private val _otpSendState = MutableStateFlow<Resource<OTPInfo>>(Resource.loading())
    val otpSendState: StateFlow<Resource<OTPInfo>> get() = _otpSendState

    private val _emailSendState = MutableStateFlow<Resource<EmailInfo>>(Resource.loading())
    val emailSendState: StateFlow<Resource<EmailInfo>> get() = _emailSendState

    private val _otpUIState = MutableStateFlow(OTPUIState())
    val otpUIState: StateFlow<OTPUIState> get() = _otpUIState

    fun changeOTPFirstTextValue(text: String) {
        _otpUIState.value = otpUIState.value.copy(
            firstText = text
        )
    }

    fun changeOTPSecondTextValue(text: String) {
        _otpUIState.value = otpUIState.value.copy(
            secondText = text
        )
    }

    fun changeOTPThirdTextValue(text: String) {
        _otpUIState.value = otpUIState.value.copy(
            thirdText = text
        )
    }

    fun changeOTPForthTextValue(text: String) {
        _otpUIState.value = otpUIState.value.copy(
            forthText = text
        )
    }

    fun sendOTP() {
        _otpUIState.value = otpUIState.value.copy(isLoading = true)
        coroutineScope.launch {
            val response = sendOTPUseCase(
                OTPRequest(
                    email = "abc@gmail.com",
                    otp = otpUIState.value.firstText + otpUIState.value.secondText + otpUIState.value.thirdText + otpUIState.value.forthText
                )
            )
            _otpUIState.value = otpUIState.value.copy(isLoading = response.isLoading())
            _otpSendState.tryEmit(response)
        }
    }

    fun resendEmail() {
        _otpUIState.value = otpUIState.value.copy(isLoading = true)

        coroutineScope.launch{
            val response = sendEmailUsecase(
                EmailRequest(
                    email = "abc@gmail.com",
                )
            )
            _otpUIState.value = otpUIState.value.copy(isLoading = response.isLoading())

            _emailSendState.tryEmit(response)
        }
    }

    fun clearStateOTP() {
        _otpSendState.value = Resource.loading()
    }

    fun clearStateEmail() {
        _emailSendState.value = Resource.loading()
    }
}



