package com.example.tbc_android_2025.data.repository

import com.example.tbc_android_2025.data.remote.common.ResponseHandler
import com.example.tbc_android_2025.data.remote.mapper.*
import com.example.tbc_android_2025.data.remote.service.EquipmentCategoryFetchService
import com.example.tbc_android_2025.domain.repository.*
import javax.inject.Inject

class EquipmentCategoryRepositoryImpl @Inject constructor(
    private val equipmentCategoryFetchService: EquipmentCategoryFetchService,
    private val responseHandler: ResponseHandler,
) : EquipmentCategoryRepository {

    override fun getEquipmentCategoryModelsFromRemote(): EquipmentCategoryModelsResourceFlow =
        responseHandler.safeApiCall { equipmentCategoryFetchService.getAllEquipmentCategories() }
            .asResource { it.toDomain() }

}
