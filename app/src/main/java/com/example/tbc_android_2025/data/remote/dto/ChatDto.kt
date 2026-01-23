package com.example.tbc_android_2025.data.remote.dto

import com.example.tbc_android_2025.data.remote.dto.ChatDto.Companion.GENERATE_ADAPTER
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = GENERATE_ADAPTER)
data class ChatDto(
    val id: Int,
    val image: String?,
    val owner: String,
    @property:Json(name = LAST_MESSAGE) val lastMessage: String,
    @property:Json(name = LAST_ACTIVE) val lastActive: String,
    @property:Json(name = UNREAD_MESSAGES) val unreadMessages: Int,
    @property:Json(name = IS_TYPING) val isTyping: Boolean,
    @property:Json(name = LAST_MESSAGE_TYPE) val lastMessageType: String
) {
    private companion object {
        const val GENERATE_ADAPTER = true
        const val LAST_MESSAGE = "last_message"
        const val LAST_ACTIVE = "last_active"
        const val UNREAD_MESSAGES = "unread_messages"
        const val IS_TYPING = "is_typing"
        const val LAST_MESSAGE_TYPE = "last_message_type"
    }
}
