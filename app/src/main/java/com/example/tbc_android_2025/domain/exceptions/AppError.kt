package com.example.tbc_android_2025.domain.exceptions

// TODO: gotta tidy this up
sealed class AppError {
    data class Message(val value: String) : AppError()
    data object Network : AppError()
    data object Api : AppError()
    data object State : AppError()
    data object Unknown : AppError()
    data object SearchQuery : AppError()
}
