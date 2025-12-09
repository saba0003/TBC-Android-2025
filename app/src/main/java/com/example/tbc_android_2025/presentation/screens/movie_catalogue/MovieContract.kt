package com.example.tbc_android_2025.presentation.screens.movie_catalogue

sealed interface MovieContract {

    data class State(
        val data: List<MovieModel> = emptyList(),
        val error: String? = null,
        val isLoading: Boolean = false
    )

    sealed interface Event {
        data object GetMovieModels : Event
    }

}
