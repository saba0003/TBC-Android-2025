package com.example.tbc_android_2025.data.remote.service

import com.example.tbc_android_2025.data.remote.dto.request.LoginRequestDto
import com.example.tbc_android_2025.data.remote.dto.response.LoginResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST(value = LOGIN_ENDPOINT)
    suspend fun login(@Body request: LoginRequestDto): Response<LoginResponseDto>

    private companion object {
        const val LOGIN_ENDPOINT = "login"
    }
}
