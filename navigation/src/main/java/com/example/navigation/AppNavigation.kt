package com.example.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.example.register.presentation.screen.RegisterScreen

@Composable
fun AppNavigation(initialAppRoute: AppRoute = AppRoute.Register) {
    val backStack = remember { mutableStateListOf(initialAppRoute) }

    NavDisplay(backStack = backStack) { key ->
        NavEntry(key = key) {
            when (key) {
                AppRoute.Register -> RegisterScreen()
            }
        }
    }
}
