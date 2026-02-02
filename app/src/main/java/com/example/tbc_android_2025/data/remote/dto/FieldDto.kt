package com.example.tbc_android_2025.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FieldDto(
    @SerialName(value = FIELD_ID) val fieldId: Int,
    @SerialName(value = FIELD_TYPE) val fieldType: String,
    val hint: String,
    val icon: String,
    @SerialName(value = IS_ACTIVE) val isActive: Boolean,
    val keyboard: String?,
    val required: Boolean
) {
    private companion object {
        const val FIELD_ID = "field_id"
        const val FIELD_TYPE = "field_type"
        const val IS_ACTIVE = "is_active"
    }
}
