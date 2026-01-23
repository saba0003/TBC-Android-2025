package com.example.tbc_android_2025.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.example.tbc_android_2025.presentation.screen.chats.ChatsScreen

@Composable
fun AppNavigation(initialRoute: Route = Route.Orders) {
    val backStack = remember { mutableStateListOf(initialRoute) }

    NavDisplay(backStack = backStack) { key ->
        NavEntry(key) {
            when (it) {
                Route.Orders -> ChatsScreen()
            }
        }
    }
}
