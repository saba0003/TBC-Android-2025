package com.example.tbc_android_2025.presentation.mappers

import com.example.tbc_android_2025.domain.models.UsersModel
import com.example.tbc_android_2025.presentation.screen.users.Users

fun UsersModel.UserModel.toPresentation() =
    Users.User(
        id = id,
        fullName = fullName,
        email = email,
        activationStatus = activationStatus,
        lastActiveDescription = lastActiveDescription,
        lastActiveEpoch = lastActiveEpoch,
        profileImageUrl = profileImageUrl
    )

fun UsersModel.toPresentation() = Users(users = users.map { it.toPresentation() })
