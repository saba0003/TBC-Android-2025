package com.example.tbc_android_2025.domain.repositories

import com.example.tbc_android_2025.domain.commons.Resource
import com.example.tbc_android_2025.domain.models.requests.LogInRequest
import com.example.tbc_android_2025.domain.models.responses.LogInResponse
import kotlinx.coroutines.flow.Flow

interface LogInRepository {

    fun logIn(request: LogInRequest): Flow<Resource<LogInResponse>>

}
