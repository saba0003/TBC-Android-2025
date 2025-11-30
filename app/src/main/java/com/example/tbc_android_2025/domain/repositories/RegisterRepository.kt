package com.example.tbc_android_2025.domain.repositories

import com.example.tbc_android_2025.domain.commons.Resource
import com.example.tbc_android_2025.domain.models.requests.RegisterRequest
import com.example.tbc_android_2025.domain.models.responses.RegisterResponse
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {

    fun register(request: RegisterRequest): Flow<Resource<RegisterResponse>>

}
