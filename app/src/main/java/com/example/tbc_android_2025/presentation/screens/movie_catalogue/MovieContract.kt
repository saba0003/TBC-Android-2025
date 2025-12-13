package com.example.tbc_android_2025.presentation.screens.movie_catalogue

import com.example.tbc_android_2025.domain.exceptions.AppError

sealed interface MovieContract {

    // TODO: maybe use sets instead of lists

    sealed class State {
        data class Success(val data: List<MovieModel> = emptyList()) : State()
        data class Error(val error: AppError? = null, val throwable: Throwable? = null) : State()
        data class Loader(val isLoading: Boolean = false) : State()
    }

    sealed interface Event {
        data object GetMovieModels : Event
    }

    sealed interface SideEffect {
        data class ShowError(val error: AppError) : SideEffect
    }

}
