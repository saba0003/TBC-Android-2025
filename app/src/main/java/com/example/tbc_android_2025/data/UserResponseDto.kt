package com.example.tbc_android_2025.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponseDto(
    val id: Int,
    val image: String,
    val owner: String,
    @field:Json(name = "last_message") val lastMessage: String,
    @field:Json(name = "last_active") val lastActive: String,
    @field:Json(name = "unread_messages") val unreadMessages: Int,
    @field:Json(name = "is_typing") val isTyping: Boolean,
    @field:Json(name = "laste_message_type") val lastMessageType: String
)
