package com.example.tbc_android_2025.data.auth.exceptions

import com.example.tbc_android_2025.data.auth.exceptions.ExceptionStrings.REMOTE_REGISTRATION_FAILURE

sealed class RegistrationException(message: String) : RuntimeException(message) {

    class RemoteRegistrationFailed(message: String) :
        RegistrationException(
            message = String.format(format = REMOTE_REGISTRATION_FAILURE, message)
        )

}
