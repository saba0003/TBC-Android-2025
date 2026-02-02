package com.example.tbc_android_2025.data.remote.service

import com.example.tbc_android_2025.BuildConfig.BASE_URL
import com.example.tbc_android_2025.data.remote.dto.FieldDto
import retrofit2.Response
import retrofit2.http.GET

interface FieldFetchService {
    @GET(value = BASE_URL)
    suspend fun getFields(): Response<List<List<FieldDto>>>
}
