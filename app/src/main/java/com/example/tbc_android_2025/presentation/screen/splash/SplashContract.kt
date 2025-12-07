package com.example.tbc_android_2025.presentation.screen.splash

sealed interface SplashContract {

    sealed interface State {
        data object Loading : State
        data object Finished : State
    }

    sealed interface Event {
        data object OnStart : Event
        data object OnStop : Event
    }

    sealed interface SideEffect {
        data object NavigateToTemplate : SideEffect
    }

}
