package com.example.tbc_android_2025.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route : NavKey {
    @Serializable data object Home : Route
    @Serializable data object Login : Route
    @Serializable data object Register : Route
}
