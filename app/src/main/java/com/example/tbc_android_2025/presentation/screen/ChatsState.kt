package com.example.tbc_android_2025.presentation.screen

import com.example.tbc_android_2025.domain.models.Chat

data class ChatsState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val chats: List<Chat> = emptyList()
)
