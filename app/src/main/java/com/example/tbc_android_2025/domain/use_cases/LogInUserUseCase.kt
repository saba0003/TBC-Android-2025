package com.example.tbc_android_2025.domain.use_cases

import com.example.tbc_android_2025.domain.commons.Resource
import com.example.tbc_android_2025.domain.models.requests.LogInRequest
import com.example.tbc_android_2025.domain.models.responses.LogInResponse
import com.example.tbc_android_2025.domain.repositories.LogInRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogInUserUseCase @Inject constructor(private val logInRepository: LogInRepository) {

    operator fun invoke(request: LogInRequest): Flow<Resource<LogInResponse>> =
        logInRepository.logIn(request = request)

}
