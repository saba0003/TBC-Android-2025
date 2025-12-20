package com.example.tbc_android_2025.domain.use_case

import com.example.tbc_android_2025.di.qualifiers.RemoteRepository
import com.example.tbc_android_2025.domain.common.Resource
import com.example.tbc_android_2025.domain.model.TemplateModel
import com.example.tbc_android_2025.domain.repository.TemplateRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias TemplateModelsResourceFlow = Flow<Resource<List<TemplateModel>>>

class GetTemplateModelsUseCase @Inject constructor(@param:RemoteRepository private val templateRepository: TemplateRepository) {

    operator fun invoke(): TemplateModelsResourceFlow = templateRepository.getTemplateModels()

}
