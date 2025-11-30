package com.example.tbc_android_2025.data.dtos.responses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LogInResponseDto(val token: String?)
