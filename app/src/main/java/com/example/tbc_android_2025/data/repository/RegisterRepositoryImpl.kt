package com.example.tbc_android_2025.data.repository

import com.example.tbc_android_2025.data.remote.common.ResponseHandler
import com.example.tbc_android_2025.data.remote.mapper.asResource
import com.example.tbc_android_2025.data.remote.mapper.request.toData
import com.example.tbc_android_2025.data.remote.mapper.response.toDomain
import com.example.tbc_android_2025.data.remote.service.RegisterService
import com.example.tbc_android_2025.domain.model.request.RegisterRequestModel
import com.example.tbc_android_2025.domain.repository.RegisterRepository
import com.example.tbc_android_2025.domain.repository.RegisterResourceFlow
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val registerService: RegisterService,
    private val responseHandler: ResponseHandler
) : RegisterRepository {

    override fun register(request: RegisterRequestModel): RegisterResourceFlow =
        responseHandler.safeApiCall { registerService.register(request = request.toData()) }
            .asResource { it.toDomain() }

}
