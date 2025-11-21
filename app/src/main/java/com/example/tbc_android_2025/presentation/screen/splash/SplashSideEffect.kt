package com.example.tbc_android_2025.presentation.screen.splash

sealed class SplashSideEffect {

    data class NavigateToHome(val argument1: String, val argument2: String) : SplashSideEffect()

    data object NavigateToLogin : SplashSideEffect()

}
