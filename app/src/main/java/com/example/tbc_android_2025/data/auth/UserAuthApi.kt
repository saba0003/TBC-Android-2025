package com.example.tbc_android_2025.data.auth

import com.example.tbc_android_2025.data.HttpStrings.LOGIN_ENDPOINT
import com.example.tbc_android_2025.data.HttpStrings.REGISTER_ENDPOINT
import com.example.tbc_android_2025.data.auth.dtos.requests.*
import com.example.tbc_android_2025.data.auth.dtos.responses.*
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAuthApi {

    @POST(value = REGISTER_ENDPOINT)
    suspend fun register(@Body request: RegisterRequestDto): RegisterResponseDto

    @POST(value = LOGIN_ENDPOINT)
    suspend fun login(@Body request: LoginRequestDto): LoginResponseDto

}
