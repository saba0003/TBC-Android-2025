package com.example.tbc_android_2025.domain.use_case.local

import com.example.tbc_android_2025.domain.model.EquipmentCategoryModel
import com.example.tbc_android_2025.presentation.extension.walk
import javax.inject.Inject

class FilterEquipmentCategoryModelsByNameUseCase @Inject constructor() {

    operator fun invoke(query: String, categories: List<EquipmentCategoryModel>) =
        categories.asSequence()
            .flatMap { it.walk() }
            .filter { it.name.contains(other = query, ignoreCase = true) }
            .toList()

}
