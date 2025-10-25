package com.example.tbc_android_2025

import androidx.annotation.DrawableRes
import com.example.tbc_android_2025.utils.CategoryType
import com.example.tbc_android_2025.utils.CategoryType.ANY

data class Outfit(
    val id: Int = nextId(),
    @param:DrawableRes val image: Int,
    val label: String,
    val price: Int,
    val category: CategoryType = ANY
) {
    companion object {
        private var counter = 0
        private fun nextId() = ++counter
    }
}
