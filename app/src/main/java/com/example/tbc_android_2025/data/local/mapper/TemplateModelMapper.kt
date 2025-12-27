package com.example.tbc_android_2025.data.local.mapper

import com.example.tbc_android_2025.data.local.entity.TemplateEntity
import com.example.tbc_android_2025.domain.model.TemplateModel

fun TemplateModel.toEntity() = TemplateEntity(id = id)

fun List<TemplateModel>.toEntity() = map { it.toEntity() }
