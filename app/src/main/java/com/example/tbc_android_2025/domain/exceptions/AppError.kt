package com.example.tbc_android_2025.domain.exceptions

sealed class AppError {
    data class Message(val value: String) : AppError()
    data object Network : AppError()
    data object Api : AppError()
    data object State : AppError()
    data object Unknown : AppError()
}
