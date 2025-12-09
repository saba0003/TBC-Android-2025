package com.example.tbc_android_2025.data.remote.services

import com.example.tbc_android_2025.BuildConfig.GET_ALL_MOVIES_ENDPOINT
import com.example.tbc_android_2025.data.remote.dtos.MoviesResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface FetchService {

    @GET(value = GET_ALL_MOVIES_ENDPOINT)
    suspend fun getMovies(): Response<MoviesResponseDto>

}
