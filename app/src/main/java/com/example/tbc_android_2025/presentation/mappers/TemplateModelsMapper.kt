package com.example.tbc_android_2025.presentation.mappers

import com.example.tbc_android_2025.domain.models.TemplateModel as TemplateModel
import com.example.tbc_android_2025.presentation.screens.template.TemplateModels

fun TemplateModel.toPresentation() = TemplateModels.TemplateModel(id = id)

fun List<TemplateModel>.toPresentation() = TemplateModels(data = map { it.toPresentation() })
