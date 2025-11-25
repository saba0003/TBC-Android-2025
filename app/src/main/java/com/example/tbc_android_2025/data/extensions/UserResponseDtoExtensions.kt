package com.example.tbc_android_2025.data.extensions

import com.example.tbc_android_2025.data.network.ChatResponseDto
import com.example.tbc_android_2025.domain.models.Chat
import com.example.tbc_android_2025.domain.models.MessageType

fun ChatResponseDto.toDomain(): Chat = with(receiver = this) {
    Chat(
        id = id,
        image = image,
        owner = owner,
        lastMessage = lastMessage,
        lastActive = lastActive,
        unreadMessages = unreadMessages,
        isTyping = isTyping,
        lastMessageType = MessageType.fromString(value = lastMessageType)
    )
}

fun List<ChatResponseDto>.toDomainList(): List<Chat> = this.map { it.toDomain() }
