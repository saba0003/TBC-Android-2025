package com.example.tbc_android_2025.presentation.screen.login

import androidx.compose.foundation.text.input.TextFieldState

interface LoginContract {
    data class State(
        val email: TextFieldState = TextFieldState(),
        val password: TextFieldState = TextFieldState(),
        val isLoading: Boolean = NOT_YET_STARTED
    ) {
        companion object {
            private const val NOT_YET_STARTED = false
            private const val LOADING = true

            fun loading() = State(isLoading = LOADING)
        }
    }

    sealed interface Event {
        data object OnBackButtonClicked : Event
        data object OnLoginButtonClicked : Event
    }

    sealed interface SideEffect {
        data object NavigateToHomeScreen : SideEffect
    }
}
