package com.example.tbc_android_2025.data.remote.service

import com.example.tbc_android_2025.BuildConfig.ENDPOINT
import com.example.tbc_android_2025.data.remote.dto.response.EquipmentCategoryResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface EquipmentCategoryFetchService {

    @GET(value = ENDPOINT)
    suspend fun getAllEquipmentCategories(): Response<List<EquipmentCategoryResponseDto>>

}
