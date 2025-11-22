package com.example.tbc_android_2025.data

import com.example.tbc_android_2025.CommonStrings.USERS_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET

interface FetchService {

    @GET(value = USERS_ENDPOINT)
    suspend fun getUsers(): Response<List<UserResponseDto>>

}
