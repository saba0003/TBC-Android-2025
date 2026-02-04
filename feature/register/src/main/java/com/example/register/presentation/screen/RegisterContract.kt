package com.example.register.presentation.screen

import com.example.domain.error.AppError
import com.example.register.presentation.model.FieldModel

interface RegisterContract {
    data class State(
        val fields: List<List<FieldModel>> = emptyList(),
        val isLoading: Boolean = NOT_YET_STARTED
    ) {
        companion object {
            private const val NOT_YET_STARTED = false
            private const val LOADING = true

            fun loading() = State(isLoading = LOADING)
        }
    }

    sealed interface Event {
        data object OnFetchFields : Event
    }

    sealed interface SideEffect {
        data class ShowError(val error: AppError) : SideEffect
    }
}
