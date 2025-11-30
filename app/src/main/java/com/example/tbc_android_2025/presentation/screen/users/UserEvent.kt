package com.example.tbc_android_2025.presentation.screen.users

sealed class UserEvent {

    data class GetUsers(val page: Int) : UserEvent()

}
