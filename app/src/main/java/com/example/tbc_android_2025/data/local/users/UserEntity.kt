package com.example.tbc_android_2025.data.local.users

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tbc_android_2025.data.local.users.UserTable.NAME

@Entity(tableName = NAME)
data class UserEntity(
    @PrimaryKey val id: Int,
    val fullName: String,
    val email: String,
    val activationStatus: Int,
    val lastActiveDescription: String,
    val lastActiveEpoch: Int,
    val profileImageUrl: String?
)
