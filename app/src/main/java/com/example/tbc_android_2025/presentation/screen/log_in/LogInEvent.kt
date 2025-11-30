package com.example.tbc_android_2025.presentation.screen.log_in

sealed class LogInEvent {

    data class LogInUser(val request: LogInRequest) : LogInEvent()

}
