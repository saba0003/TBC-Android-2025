package com.example.tbc_android_2025.presentation.exceptions

import com.example.tbc_android_2025.presentation.ValidationStrings.EMPTY_FIELDS_ERR
import com.example.tbc_android_2025.presentation.ValidationStrings.PASSWORDS_MISMATCH_ERR
import com.example.tbc_android_2025.presentation.ValidationStrings.UNSUPPORTED_EMAIL_ERR

sealed class ValidationException(message: String) : Exception(message) {

    class EmptyFields : ValidationException(message = EMPTY_FIELDS_ERR)

    class InvalidEmail : ValidationException(message = UNSUPPORTED_EMAIL_ERR)

    class PasswordsMismatch : ValidationException(message = PASSWORDS_MISMATCH_ERR)

}
