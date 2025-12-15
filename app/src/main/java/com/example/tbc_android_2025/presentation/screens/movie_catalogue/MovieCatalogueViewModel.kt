package com.example.tbc_android_2025.presentation.screens.movie_catalogue

import androidx.lifecycle.viewModelScope
import com.example.tbc_android_2025.domain.commons.Resource.*
import com.example.tbc_android_2025.domain.use_cases.*
import com.example.tbc_android_2025.presentation.commons.BaseViewModel
import com.example.tbc_android_2025.presentation.mappers.toPresentation
import com.example.tbc_android_2025.presentation.screens.movie_catalogue.MovieContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieCatalogueViewModel @Inject constructor(
    private val getAllMovieModels: GetAllMovieModelsUseCase,
    private val getMovieModelsByTitleUseCase: GetMovieModelsByTitleUseCase
) : BaseViewModel<State, Event, SideEffect>(initialState = State.Loader(isLoading = true)) {


    init {
        onEvent(event = Event.GetAllMovieModels)
    }


    override fun onEvent(event: Event) = with(receiver = event) {
        when (this) {
            Event.GetAllMovieModels -> onGetAllMovieModels()
            is Event.GetMovieModelsByTitle -> onGetMovieModelsByTitle(title = title)
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
                        sendSideEffect(sideEffect = SideEffect.ShowError(error = it.error))
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
                        sendSideEffect(sideEffect = SideEffect.ShowError(error = it.error))
                    }
                    is Loader -> updateState { State.Loader(isLoading = it.isLoading) }
                }
            }
        }
    }
    /** ========================================================================================= */
}
