package com.example.tbc_android_2025.presentation.screen.movie_catalogue

import androidx.lifecycle.viewModelScope
import com.example.tbc_android_2025.domain.common.Resource.*
import com.example.tbc_android_2025.domain.use_case.*
import com.example.tbc_android_2025.presentation.common.BaseViewModel
import com.example.tbc_android_2025.presentation.mapper.toPresentation
import com.example.tbc_android_2025.presentation.screen.model.MovieModel
import com.example.tbc_android_2025.presentation.screen.movie_catalogue.MovieCatalogueContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieCatalogueViewModel @Inject constructor(
    private val getAllMovieModels: GetAllMovieModelsUseCase,
    private val getMovieModelsByTitleUseCase: GetMovieModelsByTitleUseCase
) : BaseViewModel<State, Event, SideEffect>(initialState = State.Loader(isLoading = true)) {


    init { onEvent(event = Event.OnGetAllMovieModels) }


    override fun onEvent(event: Event) = with(receiver = event) {
        when (this) {
            Event.OnGetAllMovieModels -> onGetAllMovieModels()
            is Event.OnGetMovieModelsByTitle -> onGetMovieModelsByTitle(title = title)
            is Event.OnMovieClicked -> onMovieClicked(movie = movie)
        }
    }


    /** ======================================= HANDLERS ======================================== */
    private fun onGetAllMovieModels() {
        viewModelScope.launch {
            getAllMovieModels().collect {
                when (it) {
                    is Success -> updateState { State.Success(data = it.data.toPresentation()) }
                    is Error -> {
                        updateState { State.Error(error = it.error) }
                        emitSideEffect(sideEffect = SideEffect.ShowError(error = it.error))
                    }
                    is Loader -> updateState { State.Loader(isLoading = it.isLoading) }
                }
            }
        }
    }

    private fun onGetMovieModelsByTitle(title: String) {
        viewModelScope.launch {
            getMovieModelsByTitleUseCase(title = title).collect {
                when (it) {
                    is Success -> updateState { State.Success(data = it.data.toPresentation()) }
                    is Error -> {
                        updateState { State.Error(error = it.error) }
                        emitSideEffect(sideEffect = SideEffect.ShowError(error = it.error))
                    }
                    is Loader -> updateState { State.Loader(isLoading = it.isLoading) }
                }
            }
        }
    }

    private fun onMovieClicked(movie: MovieModel) =
        sendSideEffect(sideEffect = SideEffect.NavigateToMovie(movie = movie))
    /** ========================================================================================= */
}
