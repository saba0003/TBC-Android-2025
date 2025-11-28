package com.example.tbc_android_2025.data.extensions

import com.example.tbc_android_2025.data.dtos.UserDto
import com.example.tbc_android_2025.domain.models.User

fun UserDto.toDomain() =
    User(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar
    )

fun List<UserDto>.toDomainList() = this.map { it.toDomain() }
