package com.example.tbc_android_2025.domain.use_case.local

import com.example.tbc_android_2025.di.qualifiers.RemoteRepository
import com.example.tbc_android_2025.domain.model.TemplateModel
import com.example.tbc_android_2025.domain.repository.TemplateRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias TemplateModelsFlow = Flow<List<TemplateModel>>

class GetTemplateModelsFromLocalUseCase @Inject constructor(
    @param:RemoteRepository private val templateRepository: TemplateRepository
) {

    operator fun invoke(): TemplateModelsFlow = templateRepository.getTemplateModelsFromLocal()

}
