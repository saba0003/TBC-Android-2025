package com.example.tbc_android_2025.data

enum class MessageType {
    TEXT, VOICE, FILE, UNKNOWN;

    companion object {
        fun fromString(value: String): MessageType =
            when (value.lowercase()) {
                "text" -> TEXT
                "voice" -> VOICE
                "file" -> FILE
                else -> UNKNOWN
            }
    }
}
