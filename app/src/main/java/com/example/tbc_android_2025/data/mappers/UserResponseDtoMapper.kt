package com.example.tbc_android_2025.data.mappers

import com.example.tbc_android_2025.data.dtos.UserResponseDto
import com.example.tbc_android_2025.data.local.models.UserEntity
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

fun List<UserResponseDto>.toDomain() = UsersModel(users = map { it.toDomain() })

fun UserResponseDto.toEntity() =
    UserEntity(
        id = id,
        fullName = fullName,
        email = email,
        activationStatus = activationStatus,
        lastActiveDescription = lastActiveDescription,
        lastActiveEpoch = lastActiveEpoch,
        profileImageUrl = profileImageUrl
    )

fun List<UserResponseDto>.toEntities() = map { it.toEntity() }
