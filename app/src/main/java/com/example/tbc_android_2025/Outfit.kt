package com.example.tbc_android_2025

import androidx.annotation.DrawableRes
import com.example.tbc_android_2025.utils.CategoryType

data class Outfit(
    @param:DrawableRes val image: Int,
    val label: String,
    val price: Int,
    val category: CategoryType
)
