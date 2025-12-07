package com.example.tbc_android_2025.data.repositories

import com.example.tbc_android_2025.data.commons.ResponseHandler
import com.example.tbc_android_2025.data.mappers.asResource
import com.example.tbc_android_2025.data.mappers.toDomainList
import com.example.tbc_android_2025.data.remote.services.TemplateService
import com.example.tbc_android_2025.domain.commons.Resource
import com.example.tbc_android_2025.domain.models.TemplateModel
import com.example.tbc_android_2025.domain.repositories.TemplateRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TemplateRepositoryImpl @Inject constructor(
    private val templateService: TemplateService,
    private val responseHandler: ResponseHandler
) : TemplateRepository {

    override fun getTemplateModels():  Flow<Resource<List<TemplateModel>>> =
        responseHandler.safeApiCall { templateService.getTemplateModels() }
            .asResource { it.toDomainList() }

}
