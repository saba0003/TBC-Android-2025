package com.example.tbc_android_2025.presentation.screen.users

sealed interface UsersEvent {

    data object GetUsers : UsersEvent

}
