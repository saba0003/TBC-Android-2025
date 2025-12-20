package com.example.tbc_android_2025.data.mapper

import com.example.tbc_android_2025.data.remote.dto.TemplateResponseDto
import com.example.tbc_android_2025.domain.model.TemplateModel

fun TemplateResponseDto.toDomain() = TemplateModel(id = id)

fun List<TemplateResponseDto>.toDomain() = this.map { it.toDomain() }
