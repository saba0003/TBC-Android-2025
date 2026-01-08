package com.example.tbc_android_2025.presentation.screen.login

import com.example.tbc_android_2025.domain.error.AppError

interface LoginContract {
    data class State(
        val email: String = "",
        val password: String = "",
        val isPasswordVisible: Boolean = false,
        val rememberMe: Boolean = false,
        val isLoading: Boolean = false
    )

    sealed interface Event {
        data class OnEmailChange(val text: String) : Event
        data class OnPasswordChange(val text: String) : Event
        data object OnEyeClick : Event
        data object OnRememberMeClick : Event
        data object OnLoginClick : Event
    }

    sealed interface SideEffect {
        data object NavigateToHome : SideEffect
        data class ShowError(val error: AppError) : SideEffect
    }
}
