package com.example.tbc_android_2025.domain.use_case.local

import com.example.tbc_android_2025.domain.model.EquipmentCategoryModel
import com.example.tbc_android_2025.domain.repository.EquipmentCategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias TemplateModelsFlow = Flow<List<EquipmentCategoryModel>>

class GetTemplateModelsFromLocalUseCase @Inject constructor(
    private val equipmentCategoryRepository: EquipmentCategoryRepository
) {

    operator fun invoke(): TemplateModelsFlow = equipmentCategoryRepository.getTemplateModelsFromLocal()

}
