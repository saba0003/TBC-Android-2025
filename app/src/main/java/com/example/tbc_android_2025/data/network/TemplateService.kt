package com.example.tbc_android_2025.data.network

import com.example.tbc_android_2025.data.network.NetworkConstants.ENDPOINT
import retrofit2.Response
import retrofit2.http.GET

interface TemplateService {

    @GET(value = ENDPOINT)
    suspend fun getTemplateModels(): Response<List<TemplateResponseDto>>

}
