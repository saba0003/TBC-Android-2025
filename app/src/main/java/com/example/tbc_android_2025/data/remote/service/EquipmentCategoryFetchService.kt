package com.example.tbc_android_2025.data.remote.service

import com.example.tbc_android_2025.data.remote.dto.response.EquipmentCategoryResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface EquipmentCategoryFetchService {

    @GET(value = ENDPOINT)
    suspend fun getAllEquipmentCategories(): Response<List<EquipmentCategoryResponseDto>>

    private companion object {
        const val ENDPOINT = "0c08be03-49c2-493b-951c-6ba8a397dc72"
    }
}
