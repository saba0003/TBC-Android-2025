package com.example.tbc_android_2025.presentation.mapper

import com.example.tbc_android_2025.domain.model.TemplateModel as TemplateModelDomain
import com.example.tbc_android_2025.presentation.screen.template.TemplateModel as TemplateModelPresentation

fun TemplateModelDomain.toPresentation() = TemplateModelPresentation(id = id)

fun List<TemplateModelDomain>.toPresentation() = map { it.toPresentation() }
