package com.example.register.data.remote.mapper

import com.example.register.data.remote.dto.FieldDto
import com.example.register.domain.model.FieldModel

private const val TO_DOMAIN_LIST = "toDomainList"
private const val TO_DOMAIN_NESTED_LIST = "toDomainNestedList"

private inline fun <reified T : Enum<T>> String.toEnum(): T =
    enumValues<T>().first { it.name.equals(other = this, ignoreCase = true) }

private inline fun <reified T : Enum<T>> String?.toEnumOrNull(): T? =
    enumValues<T>().firstOrNull { it.name.equals(other = this, ignoreCase = true) }

private fun FieldDto.toDomain(): FieldModel = FieldModel(
    fieldId = fieldId,
    hint = hint,
    fieldType = fieldType.toEnum<FieldModel.FieldType>(),
    keyboard = keyboard.toEnumOrNull<FieldModel.KeyboardType>(),
    required = required,
    isActive = isActive,
    icon = icon
)

@JvmName(name = TO_DOMAIN_LIST)
private fun List<FieldDto>.toDomain(): List<FieldModel> = map { it.toDomain() }

@JvmName(name = TO_DOMAIN_NESTED_LIST)
fun List<List<FieldDto>>.toDomain(): List<List<FieldModel>> = map { it.toDomain() }
