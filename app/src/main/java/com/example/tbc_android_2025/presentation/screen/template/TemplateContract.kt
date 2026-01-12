package com.example.tbc_android_2025.presentation.screen.template

import com.example.tbc_android_2025.domain.error.AppError

sealed interface TemplateContract {
    data class State(
        val data: List<TemplateModel> = emptyList(),
        val error: AppError? = null,
        val isLoading: Boolean = false
    ) {
        companion object {
            fun loading() = State(isLoading = true)
        }
    }

    sealed interface Event {
    }

    sealed interface SideEffect {
        data class ShowError(val error: AppError) : SideEffect
    }
}
