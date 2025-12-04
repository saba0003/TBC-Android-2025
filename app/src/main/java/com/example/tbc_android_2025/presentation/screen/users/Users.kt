package com.example.tbc_android_2025.presentation.screen.users

data class Users(val users: List<User>) {
    data class User(
        val id: Int,
        val fullName: String,
        val email: String,
        val activationStatus: Int,
        val lastActiveDescription: String,
        val lastActiveEpoch: Int,
        val profileImageUrl: String?
    )
}
