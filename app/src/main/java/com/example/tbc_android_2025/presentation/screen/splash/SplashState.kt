package com.example.tbc_android_2025.presentation.screen.splash

sealed interface SplashState {
    data object Loading : SplashState
    data object Finished : SplashState
}
