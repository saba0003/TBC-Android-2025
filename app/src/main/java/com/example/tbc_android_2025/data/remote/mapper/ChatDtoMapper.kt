package com.example.tbc_android_2025.data.remote.mapper

import com.example.tbc_android_2025.data.remote.dto.ChatDto
import com.example.tbc_android_2025.domain.model.ChatModel

fun ChatDto.toDomain(): ChatModel = ChatModel(
    id = id,
    image = image,
    owner = owner,
    lastMessage = lastMessage,
    lastActive = lastActive,
    unreadMessages = unreadMessages,
    isTyping = isTyping,
    lastMessageType = lastMessageType
)

fun List<ChatDto>.toDomain(): List<ChatModel> = map { it.toDomain() }
