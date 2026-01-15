package com.example.tbc_android_2025.presentation.navigation

sealed class Screen(val route: String) {
    object Home : Screen(route = "home_screen")
    object Login : Screen(route = "login_screen")
    object Register : Screen(route = "register_screen")
}
