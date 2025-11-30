package com.example.tbc_android_2025.data.mappers

import com.example.tbc_android_2025.data.dtos.requests.LogInRequestDto
import com.example.tbc_android_2025.data.dtos.requests.RegisterRequestDto
import com.example.tbc_android_2025.domain.models.requests.LogInRequest
import com.example.tbc_android_2025.domain.models.requests.RegisterRequest

fun RegisterRequest.toData() =
    RegisterRequestDto(email = email, password = password, repeatedPassword = repeatedPassword)

fun LogInRequest.toData() = LogInRequestDto(email = email, password = password)
