package com.example.tbc_android_2025.address

import androidx.annotation.DrawableRes
import com.example.tbc_android_2025.commons.Drawables

data class Address(
    val id: Int = nextId(),
    val shortcut: String,
    val fullLocation: String,
    @param:DrawableRes val icon: Int = Drawables.ic_home
) {
    companion object {
        private var counter = 0
        private fun nextId() = ++counter
    }
}
