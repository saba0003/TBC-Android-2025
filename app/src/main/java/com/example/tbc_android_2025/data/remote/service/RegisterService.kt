package com.example.tbc_android_2025.data.remote.service

import com.example.tbc_android_2025.data.remote.dto.request.RegisterRequestDto
import com.example.tbc_android_2025.data.remote.dto.response.RegisterResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {

    @POST(value = REGISTER_ENDPOINT)
    suspend fun register(@Body request: RegisterRequestDto): Response<RegisterResponseDto>

    private companion object {
        const val REGISTER_ENDPOINT = "register"
    }
}
