package com.example.tbc_android_2025.data.remote.service

import com.example.tbc_android_2025.data.remote.dto.response.UsersPageResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FetchService {

    @GET(value = USERS_ENDPOINT)
    suspend fun getUsersPage(@Query(value = PAGE_QUERY_PARAMETER) page: Int): Response<UsersPageResponseDto>

    private companion object {
        const val USERS_ENDPOINT = "users"
        const val PAGE_QUERY_PARAMETER = "page"
    }
}
