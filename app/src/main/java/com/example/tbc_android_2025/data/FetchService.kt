package com.example.tbc_android_2025.data

import com.example.tbc_android_2025.CommonStrings.BASE_URL
import retrofit2.Response
import retrofit2.http.GET

interface FetchService {

    @GET(value = BASE_URL)
    suspend fun getUsers(): Response<List<UserResponseDto>>

}
