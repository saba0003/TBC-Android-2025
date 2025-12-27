package com.example.tbc_android_2025.presentation.screen.splash

import com.example.tbc_android_2025.domain.error.AppError

interface SplashContract {

    data class State(val isLoading: Boolean = false)

    sealed interface SideEffect {
        data class ShowError(val error: AppError) : SideEffect
    }
}
