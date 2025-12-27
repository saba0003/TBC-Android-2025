package com.example.tbc_android_2025.domain.repository

import com.example.tbc_android_2025.domain.model.TemplateModel
import com.example.tbc_android_2025.domain.use_case.TemplateModelsResourceFlow

interface TemplateRepository {

    fun getTemplateModels(): TemplateModelsResourceFlow

    suspend fun downloadTemplateModels(): List<TemplateModel>

}
