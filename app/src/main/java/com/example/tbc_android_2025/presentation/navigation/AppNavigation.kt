package com.example.tbc_android_2025.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.example.tbc_android_2025.presentation.extension.resetTo
import com.example.tbc_android_2025.presentation.screen.home.HomeScreen
import com.example.tbc_android_2025.presentation.screen.login.LoginScreen
import com.example.tbc_android_2025.presentation.screen.register.RegisterScreen

@Composable
fun AppNavigation(initialRoute: Route = Route.Home) {
    val backStack = remember { mutableStateListOf(initialRoute) }

    NavDisplay(backStack = backStack) { key ->
        NavEntry(key) {
            when (it) {
                Route.Home -> HomeScreen(
                    onNavigateToLoginScreen = { backStack.add(element = Route.Login) },
                    onNavigateToRegisterScreen = { backStack.add(element = Route.Register) }
                )
                Route.Login -> LoginScreen(onNavigateToHomeScreen = { backStack.resetTo(route = Route.Home) })
                Route.Register -> RegisterScreen(onNavigateToHomeScreen = { backStack.resetTo(route = Route.Home) })
            }
        }
    }
}
