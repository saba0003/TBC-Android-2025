package com.example.tbc_android_2025.domain.error

sealed class AppError {
    data object Network : AppError()                                    // No internet
    data object ServiceUnavailable : AppError()                         // 5xx
    data object NotFound : AppError()                                   // 404
    data object Unauthorized : AppError()                               // 401
    data class ApiError(val message: String? = null) : AppError()       // Custom backend messages
    data class Technical(val message: String? = null) : AppError()      // Custom technical messages
    data object Unknown : AppError()                                    // Unknown error
}
