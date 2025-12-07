package com.example.tbc_android_2025.domain.use_cases

import com.example.tbc_android_2025.domain.commons.Resource
import com.example.tbc_android_2025.domain.models.TemplateModel
import com.example.tbc_android_2025.domain.repositories.TemplateRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTemplateModelsUseCase @Inject constructor(private val templateRepository: TemplateRepository) {

    operator fun invoke(): Flow<Resource<List<TemplateModel>>> = templateRepository.getTemplateModels()

}
