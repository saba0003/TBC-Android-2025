package com.example.tbc_android_2025.presentation.screen.login

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.tbc_android_2025.presentation.common.Strings
import com.example.tbc_android_2025.presentation.screen.login.LoginContract.*
import com.example.tbc_android_2025.presentation.ui.theme.ComfortaaFamily
import com.example.tbc_android_2025.presentation.ui.theme.FontSizeLarge

private const val BORDER_RADIUS = 6

// This wrapper keeps the VM logic separate from the UI logic
@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel(), onNavigateToHomeScreen: () -> Unit) {
    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect {
            when (it) {
                SideEffect.NavigateToHomeScreen -> onNavigateToHomeScreen()
            }
        }
    }

    LoginContent(
        emailState = viewModel.emailState,
        passwordState = viewModel.passwordState,
        onBackClick = { viewModel.onEvent(event = Event.OnBackButtonClicked) },
        onLoginClick = { viewModel.onEvent(event = Event.OnLoginButtonClicked) }
    )
}

@Composable
fun LoginContent(
    emailState: TextFieldState,
    passwordState: TextFieldState,
    onBackClick: () -> Unit,
    onLoginClick: () -> Unit
) = Box(modifier = Modifier.fillMaxSize()) {

    // 2. The IconButton is now inside a Box, so TopStart works!
    IconButton(
        onClick = onBackClick,
        modifier = Modifier
            .padding(16.dp)
            .align(Alignment.TopStart) // This works now
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = stringResource(Strings.content_description_back_button),
            tint = Color.Black
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center // Adds space between items
    ) {
        Text(
            text = stringResource(id = Strings.login),
            modifier = Modifier.wrapContentSize(),
            fontFamily = ComfortaaFamily,
            fontSize = FontSizeLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        BasicTextField(
            state = emailState,
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Color.Black, RectangleShape),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            decorator = { innerTextField ->
                Box(modifier = Modifier.padding(17.dp)) {
                    if (emailState.text.isEmpty()) {
                        Text(stringResource(Strings.email_hint), color = Color.Gray)
                    }
                    innerTextField()
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        BasicSecureTextField(
            state = passwordState,
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Color.Black, RectangleShape),
            decorator = { innerTextField ->
                Box(modifier = Modifier.padding(17.dp)) {
                    // FIXED: Now checking passwordState
                    if (passwordState.text.isEmpty()) {
                        Text(stringResource(Strings.password_hint), color = Color.Gray)
                    }
                    innerTextField()
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onLoginClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(BORDER_RADIUS.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text(stringResource(Strings.login), fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoginScreenPreview() {
    // Preview now works because we don't call hiltViewModel() here
    LoginContent(
        emailState = rememberTextFieldState(),
        passwordState = rememberTextFieldState(),
        onBackClick = {},
        onLoginClick = {}
    )
}
