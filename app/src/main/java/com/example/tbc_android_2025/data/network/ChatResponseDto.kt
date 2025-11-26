package com.example.tbc_android_2025.data.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChatResponseDto(
    val id: Int,
    val image: String?,
    val owner: String,
    @field:Json(name = LAST_MESSAGE) val lastMessage: String,
    @field:Json(name = LAST_ACTIVE) val lastActive: String,
    @field:Json(name = UNREAD_MESSAGES) val unreadMessages: Int,
    @field:Json(name = IS_TYPING) val isTyping: Boolean,
    @field:Json(name = LAST_MESSAGE_TYPE) val lastMessageType: String
) {
    private companion object {
        const val LAST_MESSAGE = "last_message"
        const val LAST_ACTIVE = "last_active"
        const val UNREAD_MESSAGES = "unread_messages"
        const val IS_TYPING = "is_typing"
        const val LAST_MESSAGE_TYPE = "laste_message_type"
    }
}
