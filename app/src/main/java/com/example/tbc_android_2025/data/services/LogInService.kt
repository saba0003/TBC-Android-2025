package com.example.tbc_android_2025.data.services

import com.example.tbc_android_2025.data.dtos.requests.LogInRequestDto
import com.example.tbc_android_2025.data.dtos.responses.LogInResponseDto
import com.example.tbc_android_2025.data.network.NetworkConstants.LOGIN_ENDPOINT
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LogInService {

    @POST(value = LOGIN_ENDPOINT)
    suspend fun logIn(@Body request: LogInRequestDto): Response<LogInResponseDto>
    
}
