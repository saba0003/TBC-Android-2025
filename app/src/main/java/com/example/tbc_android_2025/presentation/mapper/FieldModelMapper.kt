package com.example.tbc_android_2025.presentation.mapper

import com.example.tbc_android_2025.domain.model.FieldModel as FieldModelDomain
import com.example.tbc_android_2025.presentation.model.FieldModel as FieldModelPresentation

private const val TO_PRESENTATION_LIST = "toPresentationList"
private const val TO_PRESENTATION_NESTED_LIST = "toPresentationNestedList"

private fun FieldModelDomain.FieldType.toPresentation() =
    FieldModelPresentation.FieldType.valueOf(value = name)

private fun FieldModelDomain.KeyboardType.toPresentation() =
    FieldModelPresentation.KeyboardType.valueOf(value = name)

private fun FieldModelDomain.toPresentation() = FieldModelPresentation(
    fieldId = fieldId,
    hint = hint,
    fieldType = fieldType.toPresentation(),
    keyboard = keyboard?.toPresentation(),
    required = required,
    isActive = isActive,
    icon = icon
)

@JvmName(name = TO_PRESENTATION_LIST)
private fun List<FieldModelDomain>.toPresentation() = map { it.toPresentation() }

@JvmName(name = TO_PRESENTATION_NESTED_LIST)
fun List<List<FieldModelDomain>>.toPresentation() = map { it.toPresentation() }
