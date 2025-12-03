package com.example.tbc_android_2025.domain.validation

sealed interface PasscodeValidationResult {
    data object Success : PasscodeValidationResult
    data object Failure : PasscodeValidationResult
}
