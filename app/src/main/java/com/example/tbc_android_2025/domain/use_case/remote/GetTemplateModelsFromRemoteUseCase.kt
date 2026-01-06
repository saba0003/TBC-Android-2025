package com.example.tbc_android_2025.domain.use_case.remote

import com.example.tbc_android_2025.domain.common.Resource
import com.example.tbc_android_2025.domain.model.TemplateModel
import com.example.tbc_android_2025.domain.repository.TemplateRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias TemplateModelsResourceFlow = Flow<Resource<List<TemplateModel>>>

class GetTemplateModelsFromRemoteUseCase @Inject constructor(private val templateRepository: TemplateRepository) {

    operator fun invoke(): TemplateModelsResourceFlow =
        templateRepository.getTemplateModelsFromRemote()

}
