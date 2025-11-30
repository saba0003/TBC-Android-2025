package com.example.tbc_android_2025.domain.validations.register

sealed interface RegistrationValidationResult {

    data object Success : RegistrationValidationResult

    sealed interface Error : RegistrationValidationResult {
        data object EmptyFields : Error
        data object InvalidEmail : Error
        data object PasswordsMismatch : Error
    }

}
