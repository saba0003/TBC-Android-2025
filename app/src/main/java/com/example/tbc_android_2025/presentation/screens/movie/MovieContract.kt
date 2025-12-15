package com.example.tbc_android_2025.presentation.screens.movie

import com.example.tbc_android_2025.presentation.screens.commons.MovieModel

sealed interface MovieContract {

    sealed interface State {
        data object Loading : State
        data class Ready(val movie: MovieModel) : State
    }

    sealed interface Event {
        data class Init(val movie: MovieModel) : Event
        data object OnWatchTrailer : Event
        data object OnBackButtonPressed : Event
    }

    sealed interface SideEffect {
        data object NavigateBack : SideEffect
        data class PlayTrailer(val url: String) : SideEffect
    }

}
