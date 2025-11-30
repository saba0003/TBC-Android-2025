package com.example.tbc_android_2025.presentation.screen.register


// TODO: I think it's better to be refactored as sealed class and maybe also add Idle state
data class RegisterState(
    val isSuccess: Boolean = false,
    val error: String? = null,
    val isLoading: Boolean = false
)
