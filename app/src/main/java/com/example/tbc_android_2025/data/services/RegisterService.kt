package com.example.tbc_android_2025.data.services

import com.example.tbc_android_2025.data.dtos.requests.RegisterRequestDto
import com.example.tbc_android_2025.data.dtos.responses.RegisterResponseDto
import com.example.tbc_android_2025.data.network.NetworkConstants.REGISTER_ENDPOINT
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {

    @POST(value = REGISTER_ENDPOINT)
    suspend fun register(@Body request: RegisterRequestDto): Response<RegisterResponseDto>

}
