package com.example.tbc_android_2025.domain.use_case.remote

import com.example.tbc_android_2025.domain.model.EquipmentCategoryModel
import javax.inject.Inject

class FilterEquipmentCategoryModelsByNameUseCase @Inject constructor() {

    operator fun invoke(
        query: String, categories: List<EquipmentCategoryModel>
    ): List<EquipmentCategoryModel> {
        val allMatches = mutableListOf<EquipmentCategoryModel>()

        fun searchDeep(categories: List<EquipmentCategoryModel>) {
            for (category in categories) {
                if (category.name.contains(other = query, ignoreCase = true))
                    allMatches.add(element = category)
                if (category.children.isNotEmpty())
                    searchDeep(categories = category.children)
            }
        }

        searchDeep(categories = categories)

        return allMatches
    }

}
