package com.example.tbc_android_2025.data.auth

import com.example.tbc_android_2025.data.HttpStrings.REGISTER_ENDPOINT
import com.example.tbc_android_2025.data.auth.dtos.requests.RegisterRequestDto
import com.example.tbc_android_2025.data.auth.dtos.responses.RegisterResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

// TODO: wrap return type with retrofit2.Response
interface RegisterService {

    @POST(value = REGISTER_ENDPOINT)
    suspend fun register(@Body request: RegisterRequestDto): RegisterResponseDto

}
