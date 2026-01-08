package com.example.tbc_android_2025.data.remote.dto.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponseDto(val token: String)
