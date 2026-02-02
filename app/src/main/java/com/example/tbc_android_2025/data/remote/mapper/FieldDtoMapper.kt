package com.example.tbc_android_2025.data.remote.mapper

import com.example.tbc_android_2025.data.remote.dto.FieldDto
import com.example.tbc_android_2025.domain.model.FieldModel

private inline fun <reified T : Enum<T>> String.toEnum(): T =
    enumValues<T>().first { it.name.equals(other = this, ignoreCase = true) }

private inline fun <reified T : Enum<T>> String?.toEnumOrNull(): T? =
    enumValues<T>().firstOrNull { it.name.equals(other = this, ignoreCase = true) }

fun FieldDto.toDomain(): FieldModel = FieldModel(
    fieldId = fieldId,
    hint = hint,
    fieldType = fieldType.toEnum<FieldModel.FieldType>(),
    keyboard = keyboard.toEnumOrNull<FieldModel.KeyboardType>(),
    required = required,
    isActive = isActive,
    icon = icon
)

fun List<FieldDto>.toDomain(): List<FieldModel> = map { it.toDomain() }

fun List<List<FieldDto>>.toDomain(): List<List<FieldModel>> = map { it.toDomain() }
