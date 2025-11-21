package com.example.tbc_android_2025.presentation.screen.splash

sealed class SplashEvent {

    data object OnStartSplash: SplashEvent()

    data object OnStopSplash: SplashEvent()

}
