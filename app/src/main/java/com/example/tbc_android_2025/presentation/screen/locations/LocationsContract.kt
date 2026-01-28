package com.example.tbc_android_2025.presentation.screen.locations

import com.example.tbc_android_2025.domain.error.AppError
import com.example.tbc_android_2025.presentation.model.LocationModel

interface LocationsContract {
    data class State(
        val locations: List<LocationModel> = emptyList(),
        val isDarkMode: Boolean = IS_DARK_MODE,
        val isLoading: Boolean = NOT_YET_STARTED
    ) {
        companion object {
            private const val NOT_YET_STARTED = false
            private const val IS_DARK_MODE = true
            private const val LOADING = true

            fun loading() = State(isLoading = LOADING)
        }
    }

    sealed interface Event {
        data object OnFetchLocations : Event
        data class OnThemeToggle(val isDark: Boolean) : Event
    }

    sealed interface SideEffect {
        data class ShowError(val error: AppError) : SideEffect
    }
}
