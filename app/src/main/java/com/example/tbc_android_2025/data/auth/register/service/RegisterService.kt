package com.example.tbc_android_2025.data.auth.register.service

import com.example.tbc_android_2025.data.HttpStrings.REGISTER_ENDPOINT
import com.example.tbc_android_2025.data.auth.register.dtos.request.RegisterRequestDto
import com.example.tbc_android_2025.data.auth.register.dtos.response.RegisterResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {

    @POST(value = REGISTER_ENDPOINT)
    suspend fun register(@Body request: RegisterRequestDto): Response<RegisterResponseDto>

}
