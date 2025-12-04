package com.example.tbc_android_2025.domain.models

data class UsersModel(val users: List<UserModel>) {
    data class UserModel(
        val id: Int,
        val fullName: String,
        val email: String,
        val activationStatus: Int,
        val lastActiveDescription: String,
        val lastActiveEpoch: Int,
        val profileImageUrl: String?
    )
}
