package com.example.tbc_android_2025.data.local.mapper

import com.example.tbc_android_2025.data.local.entity.TemplateEntity
import com.example.tbc_android_2025.domain.model.TemplateModel

fun TemplateEntity.toDomain() = TemplateModel(id = id)

fun List<TemplateEntity>.toDomain() = map { it.toDomain() }
