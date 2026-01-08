package com.example.tbc_android_2025.data.remote.dto.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequestDto(val email: String, val password: String)
