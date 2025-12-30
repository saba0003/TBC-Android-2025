package com.example.tbc_android_2025.domain.use_case.remote

import com.example.tbc_android_2025.domain.repository.EquipmentCategoryRepository
import com.example.tbc_android_2025.domain.repository.EquipmentCategoryModelsResourceFlow
import javax.inject.Inject

class GetEquipmentCategoryModelsFromRemoteUseCase @Inject constructor(
    private val equipmentCategoryRepository: EquipmentCategoryRepository
) {

    operator fun invoke(): EquipmentCategoryModelsResourceFlow =
        equipmentCategoryRepository.getEquipmentCategoryModelsFromRemote()

}
