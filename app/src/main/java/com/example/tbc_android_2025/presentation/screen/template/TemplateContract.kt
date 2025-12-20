package com.example.tbc_android_2025.presentation.screen.template

import com.example.tbc_android_2025.domain.error.AppError

sealed interface TemplateContract {
    data class State(
        val data: List<TemplateModel> = emptyList(),
        val errorCode: AppError? = null,
        val isLoading: Boolean = false
    )

    sealed interface Event {
        data object GetTemplateModels : Event
    }

    sealed interface SideEffect {
        data class ShowError(val errorCode: AppError) : SideEffect
    }
}
