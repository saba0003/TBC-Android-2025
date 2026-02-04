package com.example.presentation.screen.splash

interface SplashContract {
    data class State(val isLoading: Boolean = NOT_YET_STARTED) {
        companion object {
            private const val NOT_YET_STARTED = false
            private const val LOADING = true

            fun loading() = State(isLoading = LOADING)
        }
    }

    sealed interface SideEffect {
        data object NavigateToOrders : SideEffect
    }
}
