package com.finance2up.authentication.presentation.ui.otp

data class OTPUIState(
    val isLoading: Boolean = false,
    val firstText: String = "",
    val secondText: String = "",
    val thirdText: String = "",
    val forthText: String = ""
) {
    val enableActiveButton
        get() = !isLoading && firstText.isNotBlank() && secondText.isNotBlank() && thirdText.isNotBlank() && forthText.isNotBlank()
}
