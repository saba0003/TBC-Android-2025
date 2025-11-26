package com.example.tbc_android_2025.presentation.screen

sealed class ChatsSideEffect {

    data class ShowError(val errorMessage: String) : ChatsSideEffect()

}
