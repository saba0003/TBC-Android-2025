package com.example.tbc_android_2025.data.extensions

import com.example.tbc_android_2025.data.network.TemplateResponseDto
import com.example.tbc_android_2025.domain.models.TemplateModel

fun TemplateResponseDto.toDomain() = TemplateModel(id = id)

fun List<TemplateResponseDto>.toDomainList() = this.map { it.toDomain() }
