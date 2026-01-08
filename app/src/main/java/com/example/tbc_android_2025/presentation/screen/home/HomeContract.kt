package com.example.tbc_android_2025.presentation.screen.home

import com.example.tbc_android_2025.domain.error.AppError

interface HomeContract {
    data class State(val isLoading: Boolean = false) {
        companion object {
            fun loading() = State(isLoading = true)
        }
    }

    sealed interface Event {
        data object OnProfileClick : Event
    }

    sealed interface SideEffect {
        data object NavigateToProfile : SideEffect
        data class ShowError(val error: AppError) : SideEffect
    }
}
