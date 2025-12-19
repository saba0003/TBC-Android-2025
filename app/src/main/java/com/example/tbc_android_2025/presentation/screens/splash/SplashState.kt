package com.example.tbc_android_2025.presentation.screens.splash

sealed interface SplashState {
    data object Loading : SplashState
    data object Finished : SplashState
}
