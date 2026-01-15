package com.example.tbc_android_2025.presentation.screen.login

interface LoginContract {
    data class State(val isLoading: Boolean = false) {
        companion object {
            fun loading() = State(isLoading = true)
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
