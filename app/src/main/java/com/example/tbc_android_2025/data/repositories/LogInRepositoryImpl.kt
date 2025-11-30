package com.example.tbc_android_2025.data.repositories

import com.example.tbc_android_2025.data.commons.ResponseHandler
import com.example.tbc_android_2025.data.mappers.asResource
import com.example.tbc_android_2025.data.mappers.toData
import com.example.tbc_android_2025.data.mappers.toDomain
import com.example.tbc_android_2025.data.services.LogInService
import com.example.tbc_android_2025.domain.commons.Resource
import com.example.tbc_android_2025.domain.models.requests.LogInRequest
import com.example.tbc_android_2025.domain.models.responses.LogInResponse
import com.example.tbc_android_2025.domain.repositories.LogInRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogInRepositoryImpl @Inject constructor(
    private val logInService: LogInService,
    private val responseHandler: ResponseHandler
) : LogInRepository {

    override fun logIn(request: LogInRequest): Flow<Resource<LogInResponse>> =
        responseHandler.safeApiCall { logInService.logIn(request = request.toData()) }
            .asResource { it.toDomain() }

}
