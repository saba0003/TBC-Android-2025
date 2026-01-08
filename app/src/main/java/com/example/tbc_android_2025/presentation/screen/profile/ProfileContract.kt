package com.example.tbc_android_2025.presentation.screen.profile

interface ProfileContract {
    data class State(val isLoading: Boolean = false)

    sealed interface Event {
        data object OnHomeClick : Event
        data object OnLogoutClick : Event
    }

    sealed interface SideEffect {
        data object NavigateToHome : SideEffect
        data object NavigateToWelcome : SideEffect
    }
}
