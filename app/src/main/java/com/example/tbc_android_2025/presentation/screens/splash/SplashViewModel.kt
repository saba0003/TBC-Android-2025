package com.example.tbc_android_2025.presentation.screens.splash

import androidx.lifecycle.viewModelScope
import com.example.tbc_android_2025.presentation.commons.BaseViewModel
import com.example.tbc_android_2025.presentation.screens.splash.SplashContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() :
    BaseViewModel<State, Event, SideEffect>(initialState = State.Loading) {

    private companion object { const val DELAY = 3000L }

    private var splashJob: Job? = null

    init { onStart() }


    override fun onEvent(event: Event) {
        when (event) {
            Event.OnStart -> onStart()
            Event.OnStop -> onStop()
        }
    }


    /** ========================================== AUX ========================================== */
    private fun onStart() {
        splashJob = viewModelScope.launch {
            delay(timeMillis = DELAY)
            updateState { State.Finished }
            emitSideEffect(sideEffect = SideEffect.NavigateToMovieCatalogue)
        }
    }

    private fun onStop() = splashJob?.cancel()
    /** ========================================================================================= */
}
