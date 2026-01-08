package com.example.tbc_android_2025.data.remote.mapper.response

import com.example.tbc_android_2025.data.remote.dto.response.LoginResponseDto
import com.example.tbc_android_2025.domain.model.response.LoginResponseModel

fun LoginResponseDto.toDomain(): LoginResponseModel = LoginResponseModel(token = token)
