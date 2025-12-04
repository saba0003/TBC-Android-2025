package com.example.tbc_android_2025.presentation.screen.splash

sealed interface SplashEvent {
    data object OnStartSplash : SplashEvent
    data object OnStopSplash : SplashEvent
}
