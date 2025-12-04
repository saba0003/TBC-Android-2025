package com.example.tbc_android_2025.domain.validation

class ValidatePasscodeUseCase(private val correctPasscode: String) {

    operator fun invoke(input: String): PasscodeValidationResult {
        return if (input == correctPasscode)
            PasscodeValidationResult.Success
        else
            PasscodeValidationResult.Failure
    }

}
