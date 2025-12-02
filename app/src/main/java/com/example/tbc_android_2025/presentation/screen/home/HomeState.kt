package com.example.tbc_android_2025.presentation.screen.home

data class HomeState(
    val usersPage: UsersPage? = null,
    val error: String? = null,
    val isLoading: Boolean = false
)
