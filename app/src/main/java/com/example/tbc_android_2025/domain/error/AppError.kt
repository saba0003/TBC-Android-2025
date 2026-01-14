package com.example.tbc_android_2025.domain.error

sealed class AppError {
    data object Network : AppError()                                    // No internet
    data class PhotoProcess(val message: String? = null) : AppError()
    data class PhotoUpload(val message: String? = null) : AppError()
    data object Unknown : AppError()
}
