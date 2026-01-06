package com.example.tbc_android_2025.presentation.extension

import com.example.tbc_android_2025.domain.model.EquipmentCategoryModel

fun EquipmentCategoryModel.walk(): Sequence<EquipmentCategoryModel> = sequence {
    yield(value = this@walk)
    children.forEach { yieldAll(sequence = it.walk()) }
}
