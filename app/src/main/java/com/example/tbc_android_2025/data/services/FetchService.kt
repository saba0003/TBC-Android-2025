package com.example.tbc_android_2025.data.services

import com.example.tbc_android_2025.BuildConfig
import com.example.tbc_android_2025.data.dtos.UsersResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface FetchService {

    @GET(value = BuildConfig.ENDPOINT)
    suspend fun getUsers(): Response<UsersResponseDto>

}
