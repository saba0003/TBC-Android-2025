package com.example.tbc_android_2025.domain.validations.log_in

import javax.inject.Inject
import javax.inject.Singleton

// TODO: 2 Validators are very alike
@Singleton
class LogInValidator @Inject constructor() {

    private companion object {
        const val REQRES_EMAIL_PATTERN = "^[a-zA-Z]+\\.[a-zA-Z]+@reqres\\.in$"
    }


    operator fun invoke(email: String, password: String) =
        firstError(
            checkAgainstEmptiness(email = email, password = password),
            validateEmail(email = email)
        )


    /** ===================================== AUX =============================================== */
    private fun firstError(vararg results: LogInValidationResult) =
        results.firstOrNull { it is LogInValidationResult.Error } ?: LogInValidationResult.Success

    private fun checkAgainstEmptiness(email: String, password: String) =
        if (email.isEmpty() || password.isEmpty())
            LogInValidationResult.Error.EmptyFields
        else
            LogInValidationResult.Success

    private fun validateEmail(email: String): LogInValidationResult {
        val emailPattern = Regex(pattern = REQRES_EMAIL_PATTERN)
        return if (!emailPattern.matches(input = email))
            LogInValidationResult.Error.InvalidEmail
        else
            LogInValidationResult.Success
    }
    /** ========================================================================================= */
}
