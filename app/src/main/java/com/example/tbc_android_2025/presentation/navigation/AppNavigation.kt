package com.example.tbc_android_2025.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tbc_android_2025.presentation.screen.home.HomeScreen
import com.example.tbc_android_2025.presentation.screen.login.LoginScreen
import com.example.tbc_android_2025.presentation.screen.register.RegisterScreen

@Composable
fun AppNavigation(navController: NavHostController) = NavHost(
    navController = navController, startDestination = Screen.Home.route
) {
    composable(route = Screen.Home.route) {
        HomeScreen(
            onNavigateToLoginScreen = { navController.navigate(route = Screen.Login.route) },
            onNavigateToRegisterScreen = { navController.navigate(route = Screen.Register.route) }
        )
    }

    composable(route = Screen.Login.route) {
        LoginScreen(onNavigateToHomeScreen = { navController.navigate(route = Screen.Home.route) })
    }

    composable(route = Screen.Register.route) {
        RegisterScreen(onNavigateToHomeScreen = { navController.navigate(route = Screen.Home.route) })
    }
}
