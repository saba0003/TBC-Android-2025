package com.example.tbc_android_2025.presentation.screens.splash

import androidx.lifecycle.viewModelScope
import com.example.tbc_android_2025.domain.commons.Resource
import com.example.tbc_android_2025.domain.use_cases.GetAllMovieModelsUseCase
import com.example.tbc_android_2025.presentation.commons.BaseViewModel
import com.example.tbc_android_2025.presentation.screens.splash.SplashState as State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val getAllMovieModels: GetAllMovieModelsUseCase) :
    BaseViewModel<State, Unit, Unit>(initialState = State.Loading) {

    init {
        viewModelScope.launch {
            getAllMovieModels()
                .filterIsInstance<Resource.Loader>()
                .first { it.isLoading.not() }
            updateState { State.Finished }
        }
    }
}
