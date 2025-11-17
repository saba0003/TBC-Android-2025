package com.example.tbc_android_2025.data.auth.exceptions

import com.example.tbc_android_2025.data.auth.exceptions.ExceptionStrings.REMOTE_LOGIN_FAILURE
import com.example.tbc_android_2025.data.auth.exceptions.ExceptionStrings.USER_NOT_FOUND_LOCALLY_ERR

sealed class LoginException(message: String) : RuntimeException(message) {

    class UserNotFound : LoginException(message = USER_NOT_FOUND_LOCALLY_ERR)

    class RemoteLoginFailed(message: String) :
        LoginException(message = String.format(format = REMOTE_LOGIN_FAILURE, message))

}
