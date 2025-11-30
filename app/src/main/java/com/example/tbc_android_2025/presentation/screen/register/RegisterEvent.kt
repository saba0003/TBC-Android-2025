package com.example.tbc_android_2025.presentation.screen.register

sealed class RegisterEvent {

    data class RegisterUser(val request: RegisterRequest) : RegisterEvent()

}
