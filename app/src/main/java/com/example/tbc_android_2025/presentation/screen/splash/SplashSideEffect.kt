package com.example.tbc_android_2025.presentation.screen.splash

sealed interface SplashSideEffect {

    data object NavigateToTemplate : SplashSideEffect

}
