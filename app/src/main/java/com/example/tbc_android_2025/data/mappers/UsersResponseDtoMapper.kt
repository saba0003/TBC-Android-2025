package com.example.tbc_android_2025.data.mappers

import com.example.tbc_android_2025.data.dtos.UsersResponseDto
import com.example.tbc_android_2025.domain.models.UsersModel

fun UsersResponseDto.UserDto.toDomain() =
    UsersModel.UserModel(
        id = id,
        fullName = fullName,
        email = email,
        activationStatus = activationStatus,
        lastActiveDescription = lastActiveDescription,
        lastActiveEpoch = lastActiveEpoch,
        profileImageUrl = profileImageUrl
    )

fun UsersResponseDto.toDomain() = UsersModel(users = users.map { it.toDomain() })
