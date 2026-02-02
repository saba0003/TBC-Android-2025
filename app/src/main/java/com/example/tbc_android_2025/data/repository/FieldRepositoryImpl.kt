package com.example.tbc_android_2025.data.repository

import com.example.tbc_android_2025.data.remote.common.ResponseHandler
import com.example.tbc_android_2025.data.remote.mapper.asResource
import com.example.tbc_android_2025.data.remote.mapper.toDomain
import com.example.tbc_android_2025.data.remote.service.FieldFetchService
import com.example.tbc_android_2025.domain.repository.FieldRepository
import com.example.tbc_android_2025.domain.repository.FieldsResourceFlow
import javax.inject.Inject

class FieldRepositoryImpl @Inject constructor(
    private val fieldFetchService: FieldFetchService,
    private val responseHandler: ResponseHandler
) : FieldRepository {

    override fun getFields(): FieldsResourceFlow =
        responseHandler.safeApiCall { fieldFetchService.getFields() }
            .asResource { it.toDomain() }

}
