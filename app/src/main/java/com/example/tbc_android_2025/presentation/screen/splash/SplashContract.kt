package com.example.tbc_android_2025.presentation.screen.splash

import com.example.tbc_android_2025.domain.error.AppError
import com.example.tbc_android_2025.presentation.screen.template.TemplateModel

interface SplashContract {
    data class State(val data: List<TemplateModel> = emptyList(), val isLoading: Boolean = false) {
        companion object {
            fun loading() = State(isLoading = true)
        }
    }

    sealed interface Event {
        data object OnLoadTemplateModelsFromRemoteAndProceed : Event
        data object OnLoadTemplateModelsFromLocalAndProceed : Event
    }

    sealed interface SideEffect {
        data class ShowError(val error: AppError) : SideEffect
    }
}
