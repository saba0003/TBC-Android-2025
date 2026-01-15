package com.example.tbc_android_2025.presentation.screen.home

interface HomeContract {
    data class State(val isLoading: Boolean = false) {
        companion object {
            fun loading() = State(isLoading = true)
        }
    }

    sealed interface Event {
        data object OnLoginButtonClicked : Event
        data object OnRegisterButtonClicked : Event
    }

    sealed interface SideEffect {
        data object NavigateToLoginScreen : SideEffect
        data object NavigateToRegisterScreen : SideEffect
    }
}