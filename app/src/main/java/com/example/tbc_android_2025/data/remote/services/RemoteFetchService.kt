package com.example.tbc_android_2025.data.remote.services

import com.example.tbc_android_2025.BuildConfig
import com.example.tbc_android_2025.data.remote.dtos.UserResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface RemoteFetchService {

    @GET(value = BuildConfig.ENDPOINT)
    suspend fun getUsers(): Response<List<UserResponseDto>>

}
