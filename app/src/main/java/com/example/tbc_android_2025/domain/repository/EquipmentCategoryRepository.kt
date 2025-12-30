package com.example.tbc_android_2025.domain.repository

import com.example.tbc_android_2025.domain.common.Resource
import com.example.tbc_android_2025.domain.model.EquipmentCategoryModel
import kotlinx.coroutines.flow.Flow

typealias EquipmentCategoryModelsResourceFlow = Flow<Resource<List<EquipmentCategoryModel>>>

interface EquipmentCategoryRepository {

    fun getEquipmentCategoryModelsFromRemote(): EquipmentCategoryModelsResourceFlow

}
