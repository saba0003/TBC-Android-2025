package com.example.tbc_android_2025.data.mappers

import com.example.tbc_android_2025.data.dtos.responses.LogInResponseDto
import com.example.tbc_android_2025.data.dtos.responses.RegisterResponseDto
import com.example.tbc_android_2025.domain.models.responses.LogInResponse
import com.example.tbc_android_2025.domain.models.responses.RegisterResponse

fun RegisterResponseDto.toDomain() = RegisterResponse(id = id, token = token)

fun LogInResponseDto.toDomain() = LogInResponse(token = token)
