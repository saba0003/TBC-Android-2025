package com.example.tbc_android_2025.presentation.screen.log_in

sealed class LogInSideEffect {

    data class ShowError(val errorMessage: String) : LogInSideEffect()

}
