package com.example.tbc_android_2025.presentation.mappers

import com.example.tbc_android_2025.presentation.screen.users.User
import com.example.tbc_android_2025.presentation.screen.users.UsersPage as UsersPagePresentation
import com.example.tbc_android_2025.domain.models.responses.UsersPage as UsersPageDomain

fun UsersPageDomain.toPresentation() =
    UsersPagePresentation(
        page = page,
        perPage = perPage,
        total = total,
        totalPages = totalPages,
        data = data.toPresentation()
    )

fun UsersPageDomain.User.toPresentation() =
    User(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar
    )

fun List<UsersPageDomain.User>.toPresentation() = this.map { it.toPresentation() }
