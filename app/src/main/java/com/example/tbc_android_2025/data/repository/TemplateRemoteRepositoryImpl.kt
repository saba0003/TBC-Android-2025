package com.example.tbc_android_2025.data.repository

import com.example.tbc_android_2025.data.local.dao.TemplateDao
import com.example.tbc_android_2025.data.local.mapper.toDomain
import com.example.tbc_android_2025.data.remote.common.ResponseHandler
import com.example.tbc_android_2025.data.remote.mapper.asResource
import com.example.tbc_android_2025.data.remote.mapper.toDomain
import com.example.tbc_android_2025.data.remote.service.TemplateService
import com.example.tbc_android_2025.di.qualifiers.RemoteRepository
import com.example.tbc_android_2025.domain.model.TemplateModel
import com.example.tbc_android_2025.domain.repository.TemplateRepository
import com.example.tbc_android_2025.domain.use_case.TemplateModelsResourceFlow
import javax.inject.Inject

@RemoteRepository
class TemplateRemoteRepositoryImpl @Inject constructor(
    private val templateService: TemplateService,
    private val responseHandler: ResponseHandler,
    private val templateDao: TemplateDao
) : TemplateRepository {

    override fun getTemplateModels(): TemplateModelsResourceFlow =
        responseHandler.safeApiCall { templateService.getTemplateModels() }
            .asResource { it.toDomain() }

    override suspend fun downloadTemplateModels(): List<TemplateModel> =
        templateDao.downloadAll().toDomain()

}
