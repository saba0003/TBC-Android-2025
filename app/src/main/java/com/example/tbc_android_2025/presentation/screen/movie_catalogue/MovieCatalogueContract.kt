package com.example.tbc_android_2025.presentation.screen.movie_catalogue

import com.example.tbc_android_2025.domain.exception.AppError
import com.example.tbc_android_2025.presentation.screen.model.MovieModel

sealed interface MovieCatalogueContract {
    sealed class State {
        data class Success(val data: List<MovieModel> = emptyList()) : State()
        data class Error(val error: AppError? = null, val throwable: Throwable? = null) : State()
        data class Loader(val isLoading: Boolean = false) : State()
    }

    sealed interface Event {
        data object OnGetAllMovieModels : Event
        data class OnGetMovieModelsByTitle(val title: String) : Event
        data class OnMovieClicked(val movie: MovieModel) : Event
    }

    sealed interface SideEffect {
        data class NavigateToMovie(val movie: MovieModel) : SideEffect
        data class ShowError(val error: AppError) : SideEffect
    }
}
