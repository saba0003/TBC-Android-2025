package com.example.tbc_android_2025.presentation.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.example.tbc_android_2025.domain.model.UserModel as UserModelDomain
import com.example.tbc_android_2025.presentation.model.UserModel as UserModelPresentation

fun UserModelDomain.toPresentation(): UserModelPresentation = UserModelPresentation(
    id = id,
    email = email,
    firstName = firstName,
    lastName = lastName,
    avatar = avatar
)

fun PagingData<UserModelDomain>.toPresentation(): PagingData<UserModelPresentation> =
    map { it.toPresentation() }
