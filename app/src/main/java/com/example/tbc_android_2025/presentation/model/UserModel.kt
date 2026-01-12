package com.example.tbc_android_2025.presentation.model

import com.example.tbc_android_2025.presentation.common.adapter.HasId

data class UserModel(
    override val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String
) : HasId<Int>
