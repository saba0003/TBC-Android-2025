package com.example.tbc_android_2025.data.dtos.responses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterResponseDto(val id: Int?, val token: String?)
