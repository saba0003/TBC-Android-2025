package com.example.tbc_android_2025.domain.model

import kotlinx.datetime.LocalDateTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class EquipmentCategoryModel @OptIn(ExperimentalUuidApi::class) constructor(
    val id: Uuid,
    val name: String,
    val nameDe: String,
    val createdAt: LocalDateTime,
    val orderId: UByte?,
    val children: List<EquipmentCategoryModel>,
    /** Helper property; not part of the actual JSON data. */ val level: UByte
)
