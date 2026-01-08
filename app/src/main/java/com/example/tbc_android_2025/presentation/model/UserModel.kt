package com.example.tbc_android_2025.presentation.model

import com.example.tbc_android_2025.presentation.common.BasePagingAdapter

data class UserModel(
    override val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String
) : BasePagingAdapter.HasId<Int>
