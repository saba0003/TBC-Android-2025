package com.example.tbc_android_2025.data.remote.mapper.response

import com.example.tbc_android_2025.data.remote.dto.response.RegisterResponseDto
import com.example.tbc_android_2025.domain.model.response.RegisterResponseModel

fun RegisterResponseDto.toDomain(): RegisterResponseModel = RegisterResponseModel(id = id, token = token)
