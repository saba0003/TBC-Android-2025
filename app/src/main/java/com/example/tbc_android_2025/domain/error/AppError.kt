package com.example.tbc_android_2025.domain.error

sealed class AppError : Throwable() {
    data object Network : AppError()                                             // No internet
    data object ServiceUnavailable : AppError()                                  // 5xx
    data object NotFound : AppError()                                            // 404
    data object Forbidden : AppError()                                           // 403
    data object Unauthorized : AppError()                                        // 401
    data class ApiError(override val message: String? = null) : AppError()       // Custom backend messages
    data class Technical(override val message: String? = null) : AppError()      // Custom technical messages
    data object Unknown : AppError()                                             // Unknown error
}
