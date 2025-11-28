package com.example.tbc_android_2025.data.services

import com.example.tbc_android_2025.data.dtos.UsersResponseDto
import com.example.tbc_android_2025.data.network.NetworkConstants.PAGE_QUERY_PARAMETER
import com.example.tbc_android_2025.data.network.NetworkConstants.USERS_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FetchService {

    @GET(value = USERS_ENDPOINT)
    suspend fun getUsers(@Query(value = PAGE_QUERY_PARAMETER) page: Int): Response<UsersResponseDto>

}
