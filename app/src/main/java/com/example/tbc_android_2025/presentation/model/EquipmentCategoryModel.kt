package com.example.tbc_android_2025.presentation.model

import android.os.Parcelable
import com.example.tbc_android_2025.presentation.common.BaseAdapter
import kotlinx.datetime.LocalDateTime
import kotlinx.parcelize.Parcelize
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Parcelize
@OptIn(ExperimentalUuidApi::class)
data class EquipmentCategoryModel(
    override val id: Uuid,
    val name: String,
    val nameDe: String,
    val createdAt: LocalDateTime,
    val orderId: UByte?,
    val children: List<EquipmentCategoryModel>,
    /** Helper property; not part of the actual JSON data. */ val level: UByte
) : BaseAdapter.HasId<Uuid>, Parcelable
