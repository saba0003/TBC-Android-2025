package com.example.tbc_android_2025.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: Int,
    val fullName: String,
    val email: String,
    val activationStatus: Int,
    val lastActiveDescription: String,
    val lastActiveEpoch: Int,
    val profileImageUrl: String?
)
