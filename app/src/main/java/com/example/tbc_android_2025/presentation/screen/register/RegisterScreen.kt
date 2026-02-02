package com.example.tbc_android_2025.presentation.screen.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.tbc_android_2025.presentation.common.Strings
import com.example.tbc_android_2025.presentation.extension.CollectSideEffect
import com.example.tbc_android_2025.presentation.screen.register.RegisterContract.Event
import com.example.tbc_android_2025.presentation.screen.register.RegisterContract.SideEffect
import com.example.tbc_android_2025.presentation.screen.register.RegisterContract.State
import com.example.tbc_android_2025.presentation.ui.theme.AppTheme
import com.example.tbc_android_2025.presentation.ui.theme.TBCAndroid2025Theme

private const val SHOW_BACKGROUND = true

@Composable
fun RegisterScreen(viewModel: RegisterViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = AppTheme.SnackbarHostState
    val unknownError = stringResource(id = Strings.error_unknown)

    viewModel.sideEffect.CollectSideEffect {
        when (it) {
            is SideEffect.ShowError ->
                snackbarHostState.showSnackbar(message = it.error.message ?: unknownError)
        }
    }

    RegisterScreenContent(
        state = state,
        snackbarHostState = snackbarHostState,
        onEvent = { viewModel.onEvent(event = it) }
    )
}

@Composable
private fun RegisterScreenContent(
    state: State, snackbarHostState: SnackbarHostState, onEvent: (Event) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppTheme.Colors.background)
            .systemBarsPadding(),
        snackbarHost = { snackbarHostState },
        containerColor = AppTheme.Colors.background
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
        ) {}
    }
}

@Composable
@Preview(showBackground = SHOW_BACKGROUND)
private fun RegisterScreenPreview() = TBCAndroid2025Theme {
    RegisterScreenContent(
        state = State(), snackbarHostState = AppTheme.SnackbarHostState, onEvent = {}
    )
}
