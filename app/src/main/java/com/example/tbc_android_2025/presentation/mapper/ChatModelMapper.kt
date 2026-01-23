package com.example.tbc_android_2025.presentation.mapper

import com.example.tbc_android_2025.domain.model.ChatModel as ChatModelDomain
import com.example.tbc_android_2025.presentation.model.ChatModel as ChatModelPresentation

fun ChatModelDomain.toPresentation() = ChatModelPresentation(
    id = id,
    image = image,
    owner = owner,
    lastMessage = lastMessage,
    lastActive = lastActive,
    unreadMessages = unreadMessages,
    isTyping = isTyping,
    lastMessageType = lastMessageType
)

fun List<ChatModelDomain>.toPresentation() = map { it.toPresentation() }
