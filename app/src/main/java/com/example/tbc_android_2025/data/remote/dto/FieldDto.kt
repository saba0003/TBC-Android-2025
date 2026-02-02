package com.example.tbc_android_2025.data.remote.dto

import com.example.tbc_android_2025.data.remote.dto.FieldDto.Companion.GENERATE_ADAPTER
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = GENERATE_ADAPTER)
data class FieldDto(
    @property:Json(name = FIELD_ID) val fieldId: Int,
    @property:Json(name = FIELD_TYPE) val fieldType: String,
    val hint: String,
    val icon: String,
    @property:Json(name = IS_ACTIVE) val isActive: Boolean,
    val keyboard: String?,
    val required: Boolean
) {
    private companion object {
        const val GENERATE_ADAPTER = true
        const val FIELD_ID = "field_id"
        const val FIELD_TYPE = "field_type"
        const val IS_ACTIVE = "is_active"
    }
}
