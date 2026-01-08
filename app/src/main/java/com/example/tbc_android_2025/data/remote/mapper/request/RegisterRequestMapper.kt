package com.example.tbc_android_2025.data.remote.mapper.request

import com.example.tbc_android_2025.data.remote.dto.request.RegisterRequestDto
import com.example.tbc_android_2025.domain.model.request.RegisterRequestModel

fun RegisterRequestModel.toData(): RegisterRequestDto = RegisterRequestDto(email = email, password = password)
