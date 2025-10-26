package com.example.tbc_android_2025.address

import androidx.annotation.DrawableRes

data class Address(
    val id: Int = nextId(),
    val shortcut: String,
    val fullLocation: String,
    @param:DrawableRes val icon: Int
) {
    companion object {
        private var counter = 0
        private fun nextId() = ++counter
    }
}
