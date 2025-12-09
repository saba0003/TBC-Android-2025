package com.example.tbc_android_2025.data.remote.services

import com.example.tbc_android_2025.BuildConfig.ENDPOINT
import com.example.tbc_android_2025.data.remote.dtos.PostResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface FetchService {

    @GET(value = ENDPOINT)
    suspend fun getPosts(): Response<List<PostResponseDto>>

}
