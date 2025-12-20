package com.example.tbc_android_2025.data.repository

import com.example.tbc_android_2025.data.common.ResponseHandler
import com.example.tbc_android_2025.data.mapper.asResource
import com.example.tbc_android_2025.data.mapper.toDomain
import com.example.tbc_android_2025.data.remote.service.TemplateService
import com.example.tbc_android_2025.di.qualifiers.RemoteRepository
import com.example.tbc_android_2025.domain.repository.TemplateRepository
import com.example.tbc_android_2025.domain.use_case.TemplateModelsResourceFlow
import javax.inject.Inject

@RemoteRepository
class TemplateRemoteRepositoryImpl @Inject constructor(
    private val templateService: TemplateService,
    private val responseHandler: ResponseHandler
) : TemplateRepository {

    override fun getTemplateModels(): TemplateModelsResourceFlow =
        responseHandler.safeApiCall { templateService.getTemplateModels() }
            .asResource { it.toDomain() }

}
