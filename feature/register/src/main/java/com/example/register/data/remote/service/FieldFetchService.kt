package com.example.register.data.remote.service

import com.example.data.BuildConfig.BASE_URL
import com.example.register.data.remote.dto.FieldDto
import retrofit2.Response
import retrofit2.http.GET

interface FieldFetchService {
    @GET(value = BASE_URL)
    suspend fun getFields(): Response<List<List<FieldDto>>>
}
