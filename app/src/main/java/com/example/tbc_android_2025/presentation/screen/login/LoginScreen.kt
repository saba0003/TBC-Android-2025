package com.example.tbc_android_2025.presentation.screen.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.tbc_android_2025.presentation.common.Strings
import com.example.tbc_android_2025.presentation.component.AppButton
import com.example.tbc_android_2025.presentation.component.AppLoader
import com.example.tbc_android_2025.presentation.component.AppTextField
import com.example.tbc_android_2025.presentation.screen.login.LoginContract.Event
import com.example.tbc_android_2025.presentation.screen.login.LoginContract.SideEffect
import com.example.tbc_android_2025.presentation.ui.theme.ComfortaaFamily
import com.example.tbc_android_2025.presentation.ui.theme.FontSizeLarge

private const val SCREEN_PADDING = 20
private const val SPACE_IN_BETWEEN_COLUMN_ITEMS = 16
private const val SECURE = true
private const val SHOW_BACKGROUND = true

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(), onNavigateToHomeScreen: () -> Unit
) = with(receiver = viewModel) {

    val state by state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(key1 = Unit) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(state = STARTED) {
            sideEffect.collect {
                when (it) {
                    is SideEffect.NavigateToHomeScreen -> onNavigateToHomeScreen()
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LoginScreenContent(
            emailState = state.email,
            passwordState = state.email,
            onBackClick = { onEvent(event = Event.OnBackButtonClicked) },
            onLoginClick = { onEvent(event = Event.OnLoginButtonClicked) }
        )

        AppLoader(isLoading = state.isLoading)
    }
}

@Composable
private fun LoginScreenContent(
    emailState: TextFieldState,
    passwordState: TextFieldState,
    onBackClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    LoginScreenBackButton(onClick = onBackClick)
    LoginScreenBody(
        emailState = emailState, passwordState = passwordState, onLoginClick = onLoginClick
    )
}

@Composable
private fun LoginScreenBackButton(onClick: () -> Unit) = IconButton(
    onClick = onClick,
    modifier = Modifier.padding(start = SCREEN_PADDING.dp, top = SCREEN_PADDING.dp)
) {
    Icon(
        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
        contentDescription = stringResource(id = Strings.content_description_back_button),
        tint = Color.Black
    )
}

@Composable
private fun LoginScreenBody(
    emailState: TextFieldState,
    passwordState: TextFieldState,
    onLoginClick: () -> Unit
) = Column(
    modifier = Modifier
        .fillMaxSize()
        .padding(all = SCREEN_PADDING.dp),
    verticalArrangement = Arrangement.spacedBy(
        space = SPACE_IN_BETWEEN_COLUMN_ITEMS.dp, alignment = Alignment.CenterVertically
    )
) {
    LoginScreenTitle()
    LoginScreenEmailInput(state = emailState)
    LoginScreenPasswordInput(state = passwordState)
    LoginScreenLoginButton(onClick = onLoginClick)
}

@Composable
private fun LoginScreenTitle() = Text(
    text = stringResource(id = Strings.login),
    modifier = Modifier.wrapContentSize(),
    fontFamily = ComfortaaFamily,
    fontSize = FontSizeLarge
)

@Composable
private fun LoginScreenEmailInput(state: TextFieldState) = AppTextField(
    state = state,
    hint = stringResource(id = Strings.email_hint),
    keyboardType = KeyboardType.Email
)

@Composable
private fun LoginScreenPasswordInput(state: TextFieldState) = AppTextField(
    state = state,
    hint = stringResource(id = Strings.password_hint),
    isSecure = SECURE
)

@Composable
private fun LoginScreenLoginButton(onClick: () -> Unit) = AppButton(
    text = stringResource(id = Strings.login),
    onClick = onClick,
    modifier = Modifier.fillMaxWidth()
)

@Composable
@Preview(showBackground = SHOW_BACKGROUND)
private fun LoginScreenPreview() = LoginScreenContent(
    emailState = rememberTextFieldState(),
    passwordState = rememberTextFieldState(),
    onBackClick = {},
    onLoginClick = {}
)
