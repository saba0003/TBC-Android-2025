package com.example.tbc_android_2025.data.mappers

import com.example.tbc_android_2025.data.local.users.UserEntity
import com.example.tbc_android_2025.domain.models.UsersModel

fun UserEntity.toDomain() =
    UsersModel.UserModel(
        id = id,
        fullName = fullName,
        email = email,
        activationStatus = activationStatus,
        lastActiveDescription = lastActiveDescription,
        lastActiveEpoch = lastActiveEpoch,
        profileImageUrl = profileImageUrl
    )

fun List<UserEntity>.toDomain() = UsersModel(users = map { it.toDomain() })
