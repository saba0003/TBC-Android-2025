package com.example.tbc_android_2025.data.repositories

import com.example.tbc_android_2025.data.commons.ResponseHandler
import com.example.tbc_android_2025.data.mappers.asResource
import com.example.tbc_android_2025.data.mappers.toData
import com.example.tbc_android_2025.data.mappers.toDomain
import com.example.tbc_android_2025.data.services.RegisterService
import com.example.tbc_android_2025.domain.commons.Resource
import com.example.tbc_android_2025.domain.models.requests.RegisterRequest
import com.example.tbc_android_2025.domain.models.responses.RegisterResponse
import com.example.tbc_android_2025.domain.repositories.RegisterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegisterRepositoryImpl @Inject constructor(
    private val registerService: RegisterService,
    private val responseHandler: ResponseHandler
) : RegisterRepository {

    override fun register(request: RegisterRequest): Flow<Resource<RegisterResponse>> =
        responseHandler.safeApiCall { registerService.register(request = request.toData()) }
            .asResource { it.toDomain() }

}
