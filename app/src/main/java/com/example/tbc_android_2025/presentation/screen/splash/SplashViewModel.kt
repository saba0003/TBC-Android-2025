package com.example.tbc_android_2025.presentation.screen.splash

import androidx.lifecycle.viewModelScope
import com.example.tbc_android_2025.presentation.commons.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() :
    BaseViewModel<SplashState, SplashEvent, SplashSideEffect>(initialState = SplashState.Loading) {

    private companion object { const val DELAY = 3000L }

    private var splashJob: Job? = null


    init { onStartSplash() }


    override fun onEvent(event: SplashEvent) {
        when (event) {
            SplashEvent.OnStartSplash -> onStartSplash()
            SplashEvent.OnStopSplash -> onStopSplash()
        }
    }


    /** ========================================== AUX ========================================== */
    private fun onStartSplash() {
        splashJob = viewModelScope.launch {
            delay(timeMillis = DELAY)
            updateState { SplashState.Finished }
            sendEffect(sideEffect = SplashSideEffect.NavigateToTemplate)
        }
    }

    private fun onStopSplash() = splashJob?.cancel()
    /** ========================================================================================= */
}
