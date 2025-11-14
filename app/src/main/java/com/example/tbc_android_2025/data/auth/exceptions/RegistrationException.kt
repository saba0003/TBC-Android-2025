package com.example.tbc_android_2025.data.auth.exceptions

import com.example.tbc_android_2025.data.auth.exceptions.ExceptionStrings.EMPTY_FIELDS_ERR
import com.example.tbc_android_2025.data.auth.exceptions.ExceptionStrings.REMOTE_REGISTRATION_FAILURE
import com.example.tbc_android_2025.data.auth.exceptions.ExceptionStrings.UNSUPPORTED_EMAIL_ERR

sealed class RegistrationException(message: String) : Exception(message) {

    class EmptyFields : RegistrationException(message = EMPTY_FIELDS_ERR)

    class InvalidEmail : RegistrationException(message = UNSUPPORTED_EMAIL_ERR)

    class RemoteRegistrationFailed(message: String) :
        RegistrationException(message = REMOTE_REGISTRATION_FAILURE + message)

}
