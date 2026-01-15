package com.example.tbc_android_2025.data.repository

import com.example.tbc_android_2025.data.remote.common.ResponseHandler
import com.example.tbc_android_2025.data.remote.mapper.asResource
import com.example.tbc_android_2025.data.remote.mapper.toDomain
import com.example.tbc_android_2025.data.remote.service.TemplateService
import com.example.tbc_android_2025.domain.repository.TemplateRepository
import com.example.tbc_android_2025.domain.use_case.remote.TemplateModelsResourceFlow
import javax.inject.Inject

class TemplateRemoteRepositoryImpl @Inject constructor(
    private val templateService: TemplateService,
    private val responseHandler: ResponseHandler
) : TemplateRepository {

    override fun getTemplateModelsFromRemote(): TemplateModelsResourceFlow =
        responseHandler.safeApiCall { templateService.getTemplateModels() }
            .asResource { it.toDomain() }

}
