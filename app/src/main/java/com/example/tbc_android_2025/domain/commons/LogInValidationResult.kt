package com.example.tbc_android_2025.domain.commons

// TODO: too many files in commons package
sealed interface LogInValidationResult {

    data object Success : LogInValidationResult

    sealed interface Error : LogInValidationResult {
        data object EmptyFields : Error
        data object InvalidEmail : Error
    }

}
