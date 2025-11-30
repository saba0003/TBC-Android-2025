package com.example.tbc_android_2025.domain.commons

import javax.inject.Inject
import javax.inject.Singleton

// TODO: too many files in commons package
@Singleton
class RegistrationValidator @Inject constructor() : LogInValidator() {

    // TODO: Single return statement maybe
    operator fun invoke(email: String, password: String, repeatPassword: String): RegistrationValidationResult {
        val superResult = super.invoke(email = email, password = password)
        if (superResult is RegistrationValidationResult.Error)
            return superResult
        val emptinessResult = checkAgainstEmptiness(repeatPassword = repeatPassword)
        if (emptinessResult is RegistrationValidationResult.Error)
            return emptinessResult
        val passwordResult = validatePassword(password = password, repeatPassword = repeatPassword)
        if (passwordResult is RegistrationValidationResult.Error)
            return passwordResult
        return RegistrationValidationResult.Success
    }


    /** ===================================== AUX =============================================== */
    private fun checkAgainstEmptiness(repeatPassword: String) =
        if (repeatPassword.isEmpty())
            RegistrationValidationResult.Error.EmptyFields
        else
            RegistrationValidationResult.Success

    private fun validatePassword(password: String, repeatPassword: String) =
        if (password != repeatPassword)
            RegistrationValidationResult.Error.PasswordsMismatch
        else
            RegistrationValidationResult.Success
    /** ========================================================================================= */
}
