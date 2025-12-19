package com.example.tbc_android_2025.data.remote.services

import com.example.tbc_android_2025.BuildConfig.GET_ALL_MOVIES_ENDPOINT
import com.example.tbc_android_2025.BuildConfig.GET_MOVIES_BY_TITLE_ENDPOINT
import com.example.tbc_android_2025.data.remote.dtos.MoviesResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

typealias SERVER_RESPONSE = Response<MoviesResponseDto>

interface FetchService {
    @GET(value = GET_ALL_MOVIES_ENDPOINT)
    suspend fun getAllMovies(): SERVER_RESPONSE

    @GET(value = GET_MOVIES_BY_TITLE_ENDPOINT)
    suspend fun getMoviesByTitle(@Query(value = TITLE_QUERY_PARAMETER) title: String): SERVER_RESPONSE

    private companion object {
        const val TITLE_QUERY_PARAMETER = "title"
    }
}
