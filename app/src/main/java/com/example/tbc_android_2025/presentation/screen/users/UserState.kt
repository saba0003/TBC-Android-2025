package com.example.tbc_android_2025.presentation.screen.users

data class UserState(
    val usersPage: UsersPage? = null,
    val error: String? = null,
    val isLoading: Boolean = false
)
