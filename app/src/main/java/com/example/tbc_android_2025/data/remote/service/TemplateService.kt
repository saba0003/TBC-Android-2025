package com.example.tbc_android_2025.data.remote.service

import com.example.tbc_android_2025.BuildConfig.ENDPOINT
import com.example.tbc_android_2025.data.remote.dto.TemplateResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface TemplateService {

    @GET(value = ENDPOINT)
    suspend fun getTemplateModels(): Response<List<TemplateResponseDto>>

}
