package com.example.register.presentation.model

import androidx.compose.foundation.text.input.TextFieldState

data class FieldModel(
    val fieldId: Int,
    val hint: String,
    val fieldType: FieldType,
    val keyboard: KeyboardType?,
    val required: Boolean,
    val isActive: Boolean,
    val icon: String,
    val value: TextFieldState = TextFieldState()
) {
    enum class FieldType {
        CHOOSER, INPUT
    }

    enum class KeyboardType {
        NUMBER, TEXT
    }
}
