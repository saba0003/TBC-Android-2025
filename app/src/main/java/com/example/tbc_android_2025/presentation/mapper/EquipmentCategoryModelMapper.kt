package com.example.tbc_android_2025.presentation.mapper

import kotlin.uuid.ExperimentalUuidApi
import com.example.tbc_android_2025.domain.model.EquipmentCategoryModel as EquipmentCategoryModelDomain
import com.example.tbc_android_2025.presentation.screen.home.EquipmentCategoryModel as EquipmentCategoryModelPresentation

@OptIn(ExperimentalUuidApi::class)
fun EquipmentCategoryModelDomain.toPresentation() = EquipmentCategoryModelPresentation(
    id = id,
    name = name,
    nameDe = nameDe,
    createdAt = createdAt,
    orderId = orderId,
    children = children.toPresentation(),
    level = level
)

fun List<EquipmentCategoryModelDomain>.toPresentation(): List<EquipmentCategoryModelPresentation> =
    map { it.toPresentation() }
