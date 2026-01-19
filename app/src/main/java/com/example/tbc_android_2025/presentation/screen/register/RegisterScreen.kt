package com.example.tbc_android_2025.presentation.screen.register

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.tbc_android_2025.presentation.common.Strings
import com.example.tbc_android_2025.presentation.screen.component.AppButton
import com.example.tbc_android_2025.presentation.screen.component.AppTextField
import com.example.tbc_android_2025.presentation.screen.register.RegisterContract.Event
import com.example.tbc_android_2025.presentation.screen.register.RegisterContract.SideEffect
import com.example.tbc_android_2025.presentation.ui.theme.ComfortaaFamily
import com.example.tbc_android_2025.presentation.ui.theme.FontSizeLarge

private const val SCREEN_PADDING = 20
private const val SPACE_IN_BETWEEN_COLUMN_ITEMS = 16
private const val SECURE = true
private const val SHOW_BACKGROUND = true

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(), onNavigateToHomeScreen: () -> Unit
) = with(receiver = viewModel) {

    LaunchedEffect(key1 = Unit) {
        sideEffect.collect {
            when (it) {
                is SideEffect.NavigateToHomeScreen -> onNavigateToHomeScreen()
            }
        }
    }

    RegisterScreenContent(
        emailState = state.value.email,
        passwordState = state.value.password,
        onBackClick = { onEvent(event = Event.OnBackButtonClicked) },
        onNextClick = { onEvent(event = Event.OnNextButtonClicked) }
    )
}

@Composable
private fun RegisterScreenContent(
    emailState: TextFieldState,
    passwordState: TextFieldState,
    onBackClick: () -> Unit,
    onNextClick: () -> Unit
) {
    RegisterScreenBackButton(onClick = onBackClick)
    RegisterScreenBody(
        emailState = emailState, passwordState = passwordState, onNextClick = onNextClick
    )
}

@Composable
private fun RegisterScreenBackButton(onClick: () -> Unit) = IconButton(
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
private fun RegisterScreenBody(
    emailState: TextFieldState,
    passwordState: TextFieldState,
    onNextClick: () -> Unit
) = Column(
    modifier = Modifier
        .fillMaxSize()
        .padding(all = SCREEN_PADDING.dp),
    verticalArrangement = Arrangement.spacedBy(
        space = SPACE_IN_BETWEEN_COLUMN_ITEMS.dp, alignment = Alignment.CenterVertically
    )
) {
    RegisterScreenTitle()
    RegisterScreenEmailInput(state = emailState)
    RegisterScreenPasswordInput(state = passwordState)
    RegisterScreenNextButton(onClick = onNextClick)
}

@Composable
private fun RegisterScreenTitle() = Text(
    text = stringResource(id = Strings.register),
    modifier = Modifier.wrapContentSize(),
    fontFamily = ComfortaaFamily,
    fontSize = FontSizeLarge
)

@Composable
private fun RegisterScreenEmailInput(state: TextFieldState) = AppTextField(
    state = state,
    hint = stringResource(id = Strings.email_hint),
    keyboardType = KeyboardType.Email
)

@Composable
private fun RegisterScreenPasswordInput(state: TextFieldState) = AppTextField(
    state = state,
    hint = stringResource(id = Strings.password_hint),
    isSecure = SECURE
)

@Composable
private fun RegisterScreenNextButton(onClick: () -> Unit) = AppButton(
    text = stringResource(id = Strings.next),
    onClick = onClick,
    modifier = Modifier.fillMaxWidth()
)

@Composable
@Preview(showBackground = SHOW_BACKGROUND)
private fun RegisterScreenPreview() = RegisterScreenContent(
    emailState = rememberTextFieldState(),
    passwordState = rememberTextFieldState(),
    onBackClick = {},
    onNextClick = {}
)
