package com.example.tbc_android_2025.api.exceptions

import com.example.tbc_android_2025.api.ApiStrings.EMPTY_FIELDS_ERR
import com.example.tbc_android_2025.api.ApiStrings.REMOTE_LOGIN_FAILURE
import com.example.tbc_android_2025.api.ApiStrings.USER_NOT_FOUND_LOCALLY_ERR

sealed class LoginException(message: String) : RuntimeException(message) {
    class EmptyFields : LoginException(message = EMPTY_FIELDS_ERR)
    class UserNotFound : LoginException(message = USER_NOT_FOUND_LOCALLY_ERR)
    class RemoteLoginFailed(message: String) :
        LoginException(message = REMOTE_LOGIN_FAILURE + message)
}
