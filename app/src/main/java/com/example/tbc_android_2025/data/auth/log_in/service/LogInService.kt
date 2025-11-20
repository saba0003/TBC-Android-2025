package com.example.tbc_android_2025.data.auth.log_in.service

import com.example.tbc_android_2025.data.HttpStrings.LOGIN_ENDPOINT
import com.example.tbc_android_2025.data.auth.log_in.dtos.request.LogInRequestDto
import com.example.tbc_android_2025.data.auth.log_in.dtos.respsonse.LogInResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LogInService {

    @POST(value = LOGIN_ENDPOINT)
    suspend fun logIn(@Body request: LogInRequestDto) : Response<LogInResponseDto>

}
