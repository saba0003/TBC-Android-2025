package com.example.tbc_android_2025.data.services

import com.example.tbc_android_2025.data.dtos.TemplateResponseDto
import com.example.tbc_android_2025.data.network.NetworkConstants
import retrofit2.Response
import retrofit2.http.GET

interface TemplateService {

    @GET(value = NetworkConstants.ENDPOINT)
    suspend fun getTemplateModels(): Response<List<TemplateResponseDto>>

}
