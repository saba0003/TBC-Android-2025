package com.example.tbc_android_2025.domain.repositories

import com.example.tbc_android_2025.domain.commons.Resource
import com.example.tbc_android_2025.domain.models.TemplateModel
import kotlinx.coroutines.flow.Flow

interface TemplateRepository {

    fun getTemplateModels(): Flow<Resource<List<TemplateModel>>>

}
