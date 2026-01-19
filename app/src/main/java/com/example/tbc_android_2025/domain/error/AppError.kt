package com.example.tbc_android_2025.domain.error

sealed class AppError {
    data object Unknown : AppError()
}
