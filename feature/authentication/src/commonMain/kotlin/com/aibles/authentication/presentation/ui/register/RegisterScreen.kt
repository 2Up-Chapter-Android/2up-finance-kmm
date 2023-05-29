package com.aibles.authentication.presentation.ui.register

import Finance2upKMM.feature.authentication.MR
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.aibles.authentication.presentation.theme.*
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.localized
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.desc.desc

class RegisterScreen: Screen {
    @Composable
    override fun Content() {
        RegisterScreen()
    }

    @Composable
    fun RegisterScreen() {

        val canvasDrawCircle = colorResource(MR.colors.canvas_drawCircle_register)
        val canvasDrawRect = colorResource(MR.colors.canvas_drawRect_register)

        val focusManager = LocalFocusManager.current
        val interactionSource = remember { MutableInteractionSource() }

        val viewModel = rememberScreenModel { RegisterViewModel() }
        val registerState = viewModel.registerState.collectAsState()
        val registerUiState = viewModel.registerUiState.collectAsState()

    if (registerState.value.isSuccessful()) {
//        Toast.makeText(
//            context,
//            "Register Success",
//            Toast.LENGTH_SHORT,
//        ).show()
//        navController.navigate("OTPScreen")
    }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Canvas(modifier = Modifier.fillMaxSize(),
                onDraw = {
                    drawRect(color = canvasDrawRect)
                }
            )
            Canvas(modifier = Modifier.fillMaxSize(),
                onDraw = {
                    drawCircle(
                        color = canvasDrawCircle,
                        center = Offset(
                            90.dp.toPx(),
                            -100.dp.toPx()
                        ),
                        radius = 350.dp.toPx()
                    )
                }
            )

            Canvas(modifier = Modifier.fillMaxSize(),
                onDraw = {
                    drawCircle(
                        color = Color.White,
                        center = Offset(
                            350.dp.toPx(),
                            830.dp.toPx()
                        ),
                        radius = 200.dp.toPx()
                    )
                }
            )

            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .height(750.dp)
                    .padding(
                        start = margin_start_register_column,
                        end = margin_end_register_column
                    )
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) { focusManager.clearFocus() },
            ) {
                Spacer(modifier = Modifier.padding(margin_top_register_title))

            Text(
                text = MR.strings.all_signup.desc().localized(),
                color = Color.White,
                fontSize = textSize_register_title,
            )
            Text(
                text = MR.strings.register_title_two.desc().localized(),
                color = Color.White,
                fontSize = textSize_register_title,
            )

                Spacer(modifier = Modifier.padding(top = margin_bottom_register_button))

            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
            ) {
                RegisterItem(
                    text = registerUiState.value.usernameInput,
                    onValueChange = { viewModel.onUsernameValueChange(it) },
                    textLabel =  MR.strings.all_username.desc().localized(),
                    textPlaceholder = MR.strings.register_hint_username.desc().localized(),
                    keyboardType = KeyboardType.Text,
                    trailingIcon = {
                        IconButton(onClick = { viewModel.onUsernameValueChange("") }) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = MR.strings.register_clear.desc().localized(),
                                tint = LocalContentColor.current.copy(alpha = 1f),
                            )
                        }
                    }
                )
                AnimatedVisibility(visible = registerUiState.value.visibilityUsernameError) {
                    RegisterErrorText(
                        text = registerUiState.value.usernameError
                    )
                }

                Spacer(modifier = Modifier.padding(top = margin_top_register_item))
                RegisterItem(
                    text = registerUiState.value.fullNameInput,
                    onValueChange = { viewModel.onFullNameValueChange(it) },
                    textLabel = MR.strings.all_full_name.desc().localized(),
                    textPlaceholder = MR.strings.register_hint_full_name.desc().localized(),
                    keyboardType = KeyboardType.Text,
                    trailingIcon = {
                        IconButton(onClick = { viewModel.onFullNameValueChange("") }) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = MR.strings.register_clear.desc().localized(),
                                tint = LocalContentColor.current.copy(alpha = 1f),
                            )
                        }
                    }
                )
                AnimatedVisibility(visible = registerUiState.value.visibilityFullNameError) {
                    RegisterErrorText(
                        text = registerUiState.value.fullNameError
                    )
                }

                Spacer(modifier = Modifier.padding(top = margin_top_register_item))
                RegisterItem(
                    text = registerUiState.value.emailAddressInput,
                    onValueChange = { viewModel.onEmailAddressValueChange(it) },
                    textLabel = MR.strings.all_email.desc().localized(),
                    textPlaceholder = MR.strings.register_hint_email.desc().localized(),
                    keyboardType = KeyboardType.Email,
                    trailingIcon = {
                        IconButton(onClick = { viewModel.onEmailAddressValueChange("") }) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = MR.strings.register_clear.desc().localized(),
                                tint = LocalContentColor.current.copy(alpha = 1f),
                            )
                        }
                    }
                )
                AnimatedVisibility(visible = registerUiState.value.visibilityEmailAddressError) {
                    RegisterErrorText(
                        text = registerUiState.value.emailAddressError
                    )
                }

                Spacer(modifier = Modifier.padding(top = margin_top_register_item))
                RegisterPassword(
                    text = registerUiState.value.passwordInput,
                    onValueChange = { viewModel.onPasswordValueChange(it) },
                    textLabel = MR.strings.all_password.desc().localized(),
                    textPlaceholder = MR.strings.register_hint_password.desc().localized(),
                    showOrHide = false
                )
                AnimatedVisibility(visible = registerUiState.value.visibilityPasswordError) {
                    RegisterErrorText(
                        text = registerUiState.value.passwordError
                    )
                }

                Spacer(modifier = Modifier.padding(top = margin_top_register_item))
                RegisterPassword(
                    text = registerUiState.value.confirmPasswordInput,
                    onValueChange = { viewModel.onPasswordConfirmValueChange(it) },
                    textLabel = MR.strings.all_confirm_password.desc().localized(),
                    textPlaceholder = MR.strings.register_hint_confirm_password.desc().localized(),
                    showOrHide = false
                )
                AnimatedVisibility(visible = registerUiState.value.visibilityConfirmPasswordError) {
                    RegisterErrorText(
                        text = registerUiState.value.passwordConfirmError
                    )
                }

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = MR.strings.all_signup.desc().localized(),
                            color = Color.White,
                            fontSize = textSize_register_button
                        )

                        Spacer(modifier = Modifier.padding(margin_bottom_register_button))

                        Button(
                            onClick = { viewModel.registerRequest() },
                            enabled = registerUiState.value.enableRegisterButton,
                            shape = RoundedCornerShape(
                                size = radius_register_button
                            ),
                            modifier = Modifier
                                .size(100.dp)
                                .aspectRatio(1f),
                            colors = ButtonDefaults.buttonColors(colorResource(MR.colors.float_button_register)),
                        ) {
                            Icon(
                                imageVector = Icons.Filled.ArrowForward,
                                contentDescription = null,
                                tint = Color.White,
                            )
                        }
                    }

                    AnimatedVisibility(
                        visible = registerUiState.value.isLoading,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(size_register_progress),
                            color = Color.Blue,
                            strokeWidth = strokeWidth_register_progressBar
                        )
                    }
                    Spacer(modifier = Modifier.height(margin_register_progress))
                }
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = buildAnnotatedString {
                    append(MR.strings.register_already_have_account.desc().localized())
                    withStyle(SpanStyle(color = Color.Blue)) {
                        append(MR.strings.all_login.desc().localized())
                    }
                },
                fontSize = textSize_login_registerTextButton,
                modifier = Modifier
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) { }
            )
            Spacer(modifier = Modifier.height(margin_register_progress))
        }
    }
}

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun RegisterItem(
        text: String,
        onValueChange: (String) -> Unit,
        textLabel: String,
        textPlaceholder: String,
        trailingIcon: @Composable () -> Unit,
        keyboardType: KeyboardType
    ) {
        val keyboardController = LocalSoftwareKeyboardController.current

        Column(horizontalAlignment = Alignment.Start) {
            OutlinedTextField(
                value = text, onValueChange = { onValueChange(it) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(
                    topEnd = radius_register_outlineTextField,
                    bottomStart = radius_register_outlineTextField
                ),
                label = {
                    Text(
                        textLabel,
                        color = Color.White,

                        )
                },
                trailingIcon = {
                    if (text.isNotEmpty()) {
                        trailingIcon()
                    }
                },
                placeholder = {
                    Text(text = textPlaceholder)
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = keyboardType
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.DarkGray,
                ),
                singleLine = true,
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                        // i will do something here
                    }
                )
            )
        }
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun RegisterPassword(
        text: String,
        onValueChange: (String) -> Unit,
        textLabel: String,
        textPlaceholder: String,
        showOrHide: Boolean
    ) {
        val keyboardController = LocalSoftwareKeyboardController.current
        val passwordHidden = rememberSaveable { mutableStateOf(showOrHide) }

    val icon = if (passwordHidden.value)
        painterResource(MR.images.ic_show_password)
    else
        painterResource(MR.images.ic_hide_password)

        Column(horizontalAlignment = Alignment.Start) {
            OutlinedTextField(
                value = text,
                onValueChange = { onValueChange(it) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(
                    topEnd = radius_register_outlineTextField,
                    bottomStart = radius_register_outlineTextField
                ),
                label = {
                    Text(
                        textLabel,
                        color = Color.White,
                    )
                },
                placeholder = { Text(text = textPlaceholder) },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = if (passwordHidden.value) VisualTransformation.None else PasswordVisualTransformation(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.DarkGray,
                ),
                trailingIcon = {
                    if (text.isNotEmpty()) {
                        IconButton(onClick = {
                            passwordHidden.value = !passwordHidden.value
                        }) {
                            Icon(
                                painter = icon,
                                contentDescription = "",
                                tint = LocalContentColor.current.copy(alpha = 1f)
                            )
                        }
                    }
                },
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                        // i will do something here
                    }
                )
            )
        }
    }

    @Composable
    fun RegisterErrorText(text: String) {
        Row(
            Modifier
                .height(error_register_text_height)
                .wrapContentWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = padding_register_error_text)
            )
        }
    }
}