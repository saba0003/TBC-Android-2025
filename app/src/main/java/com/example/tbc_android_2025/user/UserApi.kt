package com.example.tbc_android_2025.user

import com.example.tbc_android_2025.api.requests.LoginRequest
import com.example.tbc_android_2025.api.requests.RegisterRequest
import com.example.tbc_android_2025.api.responses.LoginResponse
import com.example.tbc_android_2025.api.responses.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST(value = "register")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse

    @POST(value = "login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}
