package com.example.tbc_android_2025.domain.error

sealed class AppError {
    data class Message(val value: String) : AppError()
    data object NetworkError : AppError()
    data object ApiError : AppError()
    data object StateError : AppError()
    data object UnknownError : AppError()
}
