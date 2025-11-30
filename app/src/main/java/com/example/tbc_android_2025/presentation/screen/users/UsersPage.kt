package com.example.tbc_android_2025.presentation.screen.users

data class UsersPage(
    val page: Int,
    val perPage: Int,
    val total: Int,
    val totalPages: Int,
    val data: List<User>
)
