package com.example.tbc_android_2025.presentation.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    private val _sideEffect = MutableSharedFlow<SplashSideEffect>()
    private var splashJob: Job? = null
    val sideEffect = _sideEffect.asSharedFlow()


    fun onEvent(event: SplashEvent) {
        when (event) {
            SplashEvent.OnStartSplash -> onStartSplash()
            SplashEvent.OnStopSplash -> onStopSplash()
        }
    }

    private fun onStartSplash() {
        splashJob = viewModelScope.launch {
            delay(timeMillis = SPLASH_DELAY)
            _sideEffect.emit(value = SplashSideEffect.NavigateToLogin)
        }
    }

    private fun onStopSplash() {
        splashJob?.cancel()
    }

    companion object {
        const val SPLASH_DELAY = 5000L
    }
}
