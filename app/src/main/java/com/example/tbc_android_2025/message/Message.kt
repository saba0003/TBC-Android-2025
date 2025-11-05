package com.example.tbc_android_2025.message


import java.time.LocalDateTime


data class Message(val content: String, val sentOn: LocalDateTime = LocalDateTime.now())
