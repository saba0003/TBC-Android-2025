package com.example.tbc_android_2025.data.remote.dto.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EquipmentCategoryResponseDto(
    val id: String,
    val name: String,
    @property:Json(name = NAME_DE) val nameDe: String,
    val createdAt: String,
    @property:Json(name = BGL_NUMBER, ignore = IGNORE) val bglNumber: String? = null,
    @property:Json(name = BGL_VARIANT, ignore = IGNORE) val bglVariant: String? = null,
    @property:Json(name = ORDER_ID) val orderId: Int? = null,
    @Transient val main: String? = null,
    val children: List<EquipmentCategoryResponseDto> = emptyList()
) {
    private companion object {
        const val NAME_DE = "name_de"
        const val BGL_NUMBER = "bgl_number"
        const val BGL_VARIANT = "bgl_variant"
        const val ORDER_ID = "order_id"
        const val IGNORE = true
    }
}
