package com.example.tbc_android_2025.data.remote.mapper

import com.example.tbc_android_2025.data.remote.dto.response.UsersPageResponseDto
import com.example.tbc_android_2025.domain.model.UserModel

fun UsersPageResponseDto.UserDto.toDomain(): UserModel = UserModel(
    id = id,
    email = email,
    firstName = firstName,
    lastName = lastName,
    avatar = avatar
)

fun List<UsersPageResponseDto.UserDto>.toDomain(): List<UserModel> = map { it.toDomain() }
