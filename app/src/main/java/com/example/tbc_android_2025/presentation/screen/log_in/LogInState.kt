package com.example.tbc_android_2025.presentation.screen.log_in

// TODO: I think it's better to be refactored as sealed class and maybe also add Idle state
data class LogInState(
    val isSuccess: Boolean = false,
    val error: String? = null,
    val isLoading: Boolean = false
)
