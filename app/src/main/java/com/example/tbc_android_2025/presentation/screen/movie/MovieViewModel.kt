package com.example.tbc_android_2025.presentation.screen.movie

import com.example.tbc_android_2025.presentation.common.BaseViewModel
import com.example.tbc_android_2025.presentation.screen.movie.MovieContract.*
import com.example.tbc_android_2025.presentation.screen.movie.MovieContract.SideEffect.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor() :
    BaseViewModel<State, Event, SideEffect>(initialState = State.Loading) {

    override fun onEvent(event: Event) {
        when (event) {
            is Event.Init -> updateState { State.Ready(movie = event.movie) }
            Event.OnBackButtonPressed -> sendSideEffect(sideEffect = NavigateBack)
            Event.OnWatchTrailer -> {
                val state = state.value
                if (state is State.Ready)
                    sendSideEffect(sideEffect = PlayTrailer(url = state.movie.trailersUrls.first()))
            }
        }
    }
}
