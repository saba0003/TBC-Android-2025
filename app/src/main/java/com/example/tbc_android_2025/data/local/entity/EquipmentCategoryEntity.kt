package com.example.tbc_android_2025.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tbc_android_2025.data.local.entity.EquipmentCategoryEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class EquipmentCategoryEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val nameDe: String,
    val createdAt: String,
    val bglNumber: String? = null,
    val bglVariant: String? = null,
    val orderId: Int? = null,
    val main: String? = null,
//    val children: List<EquipmentCategoryEntity> = emptyList()
) {
    companion object {
        const val TABLE_NAME = "equipment_category"
    }
}
