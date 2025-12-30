package com.example.tbc_android_2025.data.remote.mapper

import com.example.tbc_android_2025.data.remote.dto.response.EquipmentCategoryResponseDto
import com.example.tbc_android_2025.domain.model.EquipmentCategoryModel
import com.example.tbc_android_2025.data.util.toLocalDateTime
import com.example.tbc_android_2025.data.util.toUuid
import kotlin.uuid.ExperimentalUuidApi

private const val BASE_LEVEL: UByte = 0U

@OptIn(ExperimentalUuidApi::class)
fun EquipmentCategoryResponseDto.toDomain(currentLevel: UByte = BASE_LEVEL): EquipmentCategoryModel =
    EquipmentCategoryModel(
        id = id.toUuid(),
        name = name,
        nameDe = nameDe,
        createdAt = createdAt.toLocalDateTime(),
        orderId = orderId?.toUByte(),
        children = children.toDomain(level = (currentLevel.inc())),
        level = currentLevel
    )

fun List<EquipmentCategoryResponseDto>.toDomain(level: UByte = BASE_LEVEL): List<EquipmentCategoryModel> =
    map { it.toDomain(currentLevel = level) }
