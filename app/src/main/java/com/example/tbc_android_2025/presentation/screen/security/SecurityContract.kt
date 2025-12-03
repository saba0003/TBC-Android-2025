package com.example.tbc_android_2025.presentation.screen.security

object SecurityContract {
    data class State(val input: String = "")
    sealed interface Event {
        data class DigitPressed(val digit: String) : Event
        data object BackspacePressed : Event
        data object Validate : Event
    }
    sealed interface SideEffect {
        data class ShowMessage(val text: String, val color: Int) : SideEffect
    }
}
