package com.example.tbc_android_2025.presentation.screen.splash

interface SplashContract {
    data class State(val isLoading: Boolean = false)

    sealed interface SideEffect {
        data object NavigateToWelcome : SideEffect
    }
}
