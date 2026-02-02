package com.example.tbc_android_2025.presentation.model

data class FieldModel(
    val fieldId: Int,
    val hint: String,
    val fieldType: FieldType,
    val keyboard: KeyboardType?,
    val required: Boolean,
    val isActive: Boolean,
    val icon: String
) {
    enum class FieldType {
        CHOOSER, INPUT
    }

    enum class KeyboardType {
        NUMBER, TEXT
    }
}
