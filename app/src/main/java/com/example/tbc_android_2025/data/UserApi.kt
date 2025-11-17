package com.example.tbc_android_2025.data

import com.example.tbc_android_2025.data.HttpStrings.QUERY_PARAMETER
import com.example.tbc_android_2025.data.HttpStrings.USERS_ENDPOINT
import com.example.tbc_android_2025.data.dtos.UsersResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET(value = USERS_ENDPOINT)
    suspend fun getUsers(@Query(value = QUERY_PARAMETER) page: Int = 1): UsersResponseDto

}
