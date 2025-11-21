package com.example.tbc_android_2025.data.auth

import com.example.tbc_android_2025.data.HttpStrings
import com.example.tbc_android_2025.data.dtos.UsersResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

// TODO: wrap return type with retrofit2.Response
interface FetchService {

    @GET(value = HttpStrings.USERS_ENDPOINT)
    suspend fun getUsers(@Query(value = HttpStrings.QUERY_PARAMETER) page: Int = 1): UsersResponseDto

}
