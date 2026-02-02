package com.example.tbc_android_2025.presentation.mapper

import com.example.tbc_android_2025.domain.model.FieldModel as FieldModelDomain
import com.example.tbc_android_2025.presentation.model.FieldModel as FieldModelPresentation

private fun FieldModelDomain.FieldType.toPresentation() =
    FieldModelPresentation.FieldType.valueOf(value = name)

private fun FieldModelDomain.KeyboardType.toPresentation() =
    FieldModelPresentation.KeyboardType.valueOf(value = name)

fun FieldModelDomain.toPresentation() = FieldModelPresentation(
    fieldId = fieldId,
    hint = hint,
    fieldType = fieldType.toPresentation(),
    keyboard = keyboard?.toPresentation(),
    required = required,
    isActive = isActive,
    icon = icon
)

fun List<FieldModelDomain>.toPresentation() = map { it.toPresentation() }

fun List<List<FieldModelDomain>>.toPresentation() = map { it.toPresentation() }
