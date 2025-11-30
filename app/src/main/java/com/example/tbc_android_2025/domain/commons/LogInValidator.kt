package com.example.tbc_android_2025.domain.commons

import javax.inject.Inject
import javax.inject.Singleton

// TODO: too many files in commons package
@Singleton
open class LogInValidator @Inject constructor() {

    private companion object {
        const val REQRES_EMAIL_PATTERN = "^[a-zA-Z]+\\.[a-zA-Z]+@reqres\\.in$"
    }


    // TODO: Single return statement maybe
    operator fun invoke(email: String, password: String): LogInValidationResult {
        val emptinessResult = checkAgainstEmptiness(email = email, password = password)
        if (emptinessResult is LogInValidationResult.Error)
            return emptinessResult
        val emailResult = validateEmail(email = email)
        if (emailResult is LogInValidationResult.Error)
            return emailResult
        return LogInValidationResult.Success
    }


    /** ===================================== AUX =============================================== */
    protected fun checkAgainstEmptiness(email: String, password: String) =
        if (email.isEmpty() || password.isEmpty())
            LogInValidationResult.Error.EmptyFields
        else
            LogInValidationResult.Success

    protected fun validateEmail(email: String): LogInValidationResult {
        val emailPattern = Regex(pattern = REQRES_EMAIL_PATTERN)
        return if (!emailPattern.matches(input = email))
            LogInValidationResult.Error.InvalidEmail
        else
            LogInValidationResult.Success
    }
    /** ========================================================================================= */
}
