package com.example.register.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.extension.CollectSideEffect
import com.example.presentation.ui.theme.AppTheme
import com.example.presentation.ui.theme.TBCAndroid2025Theme
import com.example.register.presentation.common.Drawables
import com.example.register.presentation.common.Strings
import com.example.register.presentation.model.FieldModel
import com.example.register.presentation.screen.RegisterContract.Event
import com.example.register.presentation.screen.RegisterContract.SideEffect
import com.example.register.presentation.screen.RegisterContract.State

val BackgroundDark = Color(0xFF1D1F24)
val CardBackground = Color(0xFF25282F)
val AccentPurple = Color(0xFF6C5DD3)
val TextSecondary = Color(0xFF808191)
private const val SHOW_BACKGROUND = true

@Composable
fun RegisterScreen(viewModel: RegisterViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = AppTheme.SnackbarHostState
    val unknownError = stringResource(id = com.example.presentation.common.Strings.error_unknown)

    viewModel.sideEffect.CollectSideEffect {
        when (it) {
            is SideEffect.ShowError ->
                snackbarHostState.showSnackbar(message = it.error.message ?: unknownError)
        }
    }

    RegisterScreenContent(
        state = state,
        snackbarHostState = snackbarHostState,
        onRegisterClicked = { viewModel.onEvent(event = it) }
    )
}

@Composable
private fun RegisterScreenContent(
    state: State,
    snackbarHostState: SnackbarHostState,
    onRegisterClicked: (Event.OnRegisterClicked) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        topBar = { RegisterScreenTopBar() },
        snackbarHost = { snackbarHostState },
        containerColor = BackgroundDark
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 100.dp) // Space for fixed button
            ) {
                items(items = state.fields) { FieldGroupCard(fields = it) }
            }

            Button(
                onClick = { onRegisterClicked },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(24.dp)
                    .height(56.dp)
                    .width(140.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AccentPurple),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(
                    text = stringResource(id = Strings.register),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
private fun RegisterScreenTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = Strings.e_auth),
            color = Color.White,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(id = Strings.register),
            color = Color(0xFF1E75FF), // The blue text in the corner
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}

@Composable
private fun FieldItem(field: FieldModel, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth().padding(vertical = 12.dp)) {
        Text(text = field.hint, color = TextSecondary, fontSize = 12.sp)

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            // Using the new BasicTextField overload
            BasicTextField(
                state = field.value, // Pass the state object directly
                modifier = Modifier.weight(1f),
                textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = if (field.keyboard == FieldModel.KeyboardType.NUMBER)
                        KeyboardType.Number else KeyboardType.Text
                ),
                cursorBrush = SolidColor(Color.White),
                decorator = { innerTextField ->
                    if (field.value.text.isEmpty()) {
                        Text(text = field.hint, color = Color.DarkGray, fontSize = 16.sp)
                    }
                    innerTextField()
                }
            )

            Icon(
                painter = painterResource(id = Drawables.ic_field_default),
                contentDescription = null,
                tint = Color.Unspecified, // Keeps original drawable colors (yellow/pink)
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // The Underline
        HorizontalDivider(
            color = Color.White.copy(alpha = 0.12f),
            thickness = 1.dp
        )
    }
}

@Composable
private fun FieldGroupCard(fields: List<FieldModel>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)) {
            fields.forEach { FieldItem(field = it) }
        }
    }
}

@Composable
@Preview(showBackground = SHOW_BACKGROUND)
private fun RegisterScreenPreview() = TBCAndroid2025Theme {
    RegisterScreenContent(
        state = State(), snackbarHostState = AppTheme.SnackbarHostState, onRegisterClicked = {}
    )
}
