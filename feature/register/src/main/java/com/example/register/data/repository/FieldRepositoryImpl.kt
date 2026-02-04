package com.example.register.data.repository

import com.example.data.remote.common.ResponseHandler
import com.example.data.remote.mapper.asResource
import com.example.register.data.remote.mapper.toDomain
import com.example.register.data.remote.service.FieldFetchService
import com.example.register.domain.repository.FieldRepository
import com.example.register.domain.repository.FieldsResourceFlow
import javax.inject.Inject

class FieldRepositoryImpl @Inject constructor(
    private val fieldFetchService: FieldFetchService,
    private val responseHandler: ResponseHandler
) : FieldRepository {

    override fun getFields(): FieldsResourceFlow =
        responseHandler.safeApiCall { fieldFetchService.getFields() }
            .asResource { it.toDomain() }

}
