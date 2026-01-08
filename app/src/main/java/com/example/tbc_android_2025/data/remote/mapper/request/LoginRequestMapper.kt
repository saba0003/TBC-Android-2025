package com.example.tbc_android_2025.data.remote.mapper.request

import com.example.tbc_android_2025.data.remote.dto.request.LoginRequestDto
import com.example.tbc_android_2025.domain.model.request.LoginRequestModel

fun LoginRequestModel.toData(): LoginRequestDto = LoginRequestDto(email = email, password = password)
