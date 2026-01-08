package com.example.tbc_android_2025.presentation.screen.welcome

interface WelcomeContract {
    data class State(val isLoading: Boolean = false) {
        companion object {
            fun loading() = State(isLoading = true)
        }
    }

    sealed interface Event {
        data object OnRegisterClick : Event
        data object OnLoginClick : Event
    }

    sealed interface SideEffect {
        data object NavigateToRegister : SideEffect
        data object NavigateToLogin : SideEffect
    }
}
