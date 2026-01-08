package com.example.tbc_android_2025.presentation.screen.register

import com.example.tbc_android_2025.domain.error.AppError

interface RegisterContract {
    data class State(
        val email: String = "",
        val username: String = "",
        val password: String = "",
        val confirmPassword: String = "",
        val isPasswordVisible: Boolean = false,
        val isLoading: Boolean = false
    )

    sealed interface Event {
        data class OnEmailChange(val text: String) : Event
        data class OnUsernameChange(val text: String) : Event
        data class OnPasswordChange(val text: String) : Event
        data class OnConfirmPasswordChange(val text: String) : Event
        data object OnEyeClick : Event
        data object OnRegisterClick : Event
    }

    sealed interface SideEffect {
        data object NavigateToHome : SideEffect
        data class ShowError(val error: AppError) : SideEffect
    }
}
