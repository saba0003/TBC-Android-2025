package com.example.tbc_android_2025.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.example.tbc_android_2025.presentation.screen.locations.LocationsScreen

@Composable
fun AppNavigation(initialAppRoute: AppRoute = AppRoute.Locations) {
    val backStack = remember { mutableStateListOf(initialAppRoute) }

    NavDisplay(backStack = backStack) { key ->
        NavEntry(key = key) {
            when (it) {
                AppRoute.Locations -> LocationsScreen()
            }
        }
    }
}
