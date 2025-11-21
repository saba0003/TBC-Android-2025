package com.example.tbc_android_2025.data.auth

import com.example.tbc_android_2025.data.HttpStrings.LOGIN_ENDPOINT
import com.example.tbc_android_2025.data.auth.dtos.requests.LoginRequestDto
import com.example.tbc_android_2025.data.auth.dtos.responses.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

// TODO: wrap return type with retrofit2.Response
interface LoginService {

    @POST(value = LOGIN_ENDPOINT)
    suspend fun logIn(@Body request: LoginRequestDto) : LoginResponseDto

}
