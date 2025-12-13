package com.example.tbc_android_2025.presentation.screens.movie_catalogue

import androidx.lifecycle.viewModelScope
import com.example.tbc_android_2025.domain.commons.Resource.*
import com.example.tbc_android_2025.domain.use_cases.GetMovieModelsUseCase
import com.example.tbc_android_2025.presentation.commons.BaseViewModel
import com.example.tbc_android_2025.presentation.mappers.toPresentation
import com.example.tbc_android_2025.presentation.screens.movie_catalogue.MovieContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieCatalogueViewModel @Inject constructor(private val getMovieModels: GetMovieModelsUseCase) :
    BaseViewModel<State, Event, SideEffect>(initialState = State.Loader(isLoading = true)) {


    init {
        onEvent(event = Event.GetMovieModels)
    }


    override fun onEvent(event: Event) = with(receiver = event) {
        when (this) {
            Event.GetMovieModels -> onGetMovieModels()
        }
    }


    /** ======================================= HANDLERS ======================================== */
    private fun onGetMovieModels() {
        viewModelScope.launch {
            getMovieModels().collect {
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
