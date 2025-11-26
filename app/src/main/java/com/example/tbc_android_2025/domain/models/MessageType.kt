package com.example.tbc_android_2025.domain.models

enum class MessageType(val value: String) {
    TEXT(value = "text"),
    VOICE(value = "voice"),
    FILE(value = "file"),
    UNKNOWN(value = "unknown");

    companion object {
        private val map = entries.associateBy(keySelector = MessageType::value)

        fun fromString(value: String): MessageType = map[value] ?: UNKNOWN
    }
}
