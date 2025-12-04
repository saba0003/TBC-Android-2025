package com.example.tbc_android_2025.data.mappers

import com.example.tbc_android_2025.data.dtos.UserResponseDto
import com.example.tbc_android_2025.domain.models.UsersModel

fun UserResponseDto.toDomain() =
    UsersModel.UserModel(
        id = id,
        fullName = fullName,
        email = email,
        activationStatus = activationStatus,
        lastActiveDescription = lastActiveDescription,
        lastActiveEpoch = lastActiveEpoch,
        profileImageUrl = profileImageUrl
    )

fun List<UserResponseDto>.toDomain() = UsersModel(users = this.map { it.toDomain() })
