package com.example.tbc_android_2025.domain.use_cases

import com.example.tbc_android_2025.domain.commons.Resource
import com.example.tbc_android_2025.domain.models.requests.RegisterRequest
import com.example.tbc_android_2025.domain.models.responses.RegisterResponse
import com.example.tbc_android_2025.domain.repositories.RegisterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(private val registerRepository: RegisterRepository) {

    operator fun invoke(request: RegisterRequest): Flow<Resource<RegisterResponse>> =
        registerRepository.register(request = request)

}
