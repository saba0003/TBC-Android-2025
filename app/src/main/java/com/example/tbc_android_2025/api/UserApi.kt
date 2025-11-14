package com.example.tbc_android_2025.api

import com.example.tbc_android_2025.api.ApiStrings.API_LOGIN_ENDPOINT
import com.example.tbc_android_2025.api.ApiStrings.API_REGISTER_ENDPOINT
import com.example.tbc_android_2025.api.requests.*
import com.example.tbc_android_2025.api.responses.*
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST(value = API_REGISTER_ENDPOINT)
    suspend fun register(@Body request: RegisterRequest): RegisterResponse

    @POST(value = API_LOGIN_ENDPOINT)
    suspend fun login(@Body request: LoginRequest): LoginResponse

}
