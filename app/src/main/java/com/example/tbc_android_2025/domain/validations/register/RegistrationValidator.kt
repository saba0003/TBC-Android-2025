package com.example.tbc_android_2025.domain.validations.register

import javax.inject.Inject
import javax.inject.Singleton

// TODO: 2 Validators are very alike
@Singleton
class RegistrationValidator @Inject constructor() {

    private companion object {
        const val REQRES_EMAIL_PATTERN = "^[a-zA-Z]+\\.[a-zA-Z]+@reqres\\.in$"
    }


    operator fun invoke(
        email: String,
        password: String,
        repeatPassword: String
    ): RegistrationValidationResult =
        firstError(
            checkAgainstEmptiness(
                email = email,
                password = password,
                repeatPassword = repeatPassword
            ),
            validateEmail(email = email),
            validatePassword(password = password, repeatPassword = repeatPassword)
        )


    /** ===================================== AUX =============================================== */
    private fun firstError(vararg results: RegistrationValidationResult): RegistrationValidationResult =
        results.firstOrNull { it is RegistrationValidationResult.Error }
            ?: RegistrationValidationResult.Success

    private fun checkAgainstEmptiness(email: String, password: String, repeatPassword: String) =
        if (email.isEmpty() || password.isEmpty() || repeatPassword.isEmpty())
            RegistrationValidationResult.Error.EmptyFields
        else
            RegistrationValidationResult.Success

    private fun validateEmail(email: String): RegistrationValidationResult {
        val emailPattern = Regex(pattern = REQRES_EMAIL_PATTERN)
        return if (!emailPattern.matches(input = email))
            RegistrationValidationResult.Error.InvalidEmail
        else
            RegistrationValidationResult.Success
    }

    private fun validatePassword(password: String, repeatPassword: String) =
        if (password != repeatPassword)
            RegistrationValidationResult.Error.PasswordsMismatch
        else
            RegistrationValidationResult.Success
    /** ========================================================================================= */
}
