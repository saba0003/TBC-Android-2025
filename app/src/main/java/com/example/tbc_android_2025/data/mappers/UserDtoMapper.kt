package com.example.tbc_android_2025.data.mappers

import com.example.tbc_android_2025.data.dtos.responses.UsersResponseDto
import com.example.tbc_android_2025.domain.models.responses.UsersPage

fun UsersResponseDto.toDomain() =
    UsersPage(
        page = page,
        perPage = perPage,
        total = total,
        totalPages = totalPages,
        data = data.toDomainList()
    )

fun UsersResponseDto.UserDto.toDomain() =
    UsersPage.User(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar
    )

fun List<UsersResponseDto.UserDto>.toDomainList() = this.map { it.toDomain() }
