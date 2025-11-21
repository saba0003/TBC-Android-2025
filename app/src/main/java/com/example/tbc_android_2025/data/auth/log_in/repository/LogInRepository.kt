package com.example.tbc_android_2025.data.auth.log_in.repository

import com.example.tbc_android_2025.data.auth.log_in.service.LogInService
import com.example.tbc_android_2025.data.auth.log_in.dtos.request.LogInRequestDto
import com.example.tbc_android_2025.data.auth.log_in.dtos.respsonse.LogInResponseDto
import com.example.tbc_android_2025.data.auth.commons.ResponseHandler
import com.example.tbc_android_2025.data.auth.commons.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias LogInResultFlow = Flow<Resource<LogInResponseDto>>

class LogInRepository @Inject constructor(
    private val authService: LogInService,
    private val responseHandler: ResponseHandler
) {
    fun logIn(email: String, password: String): LogInResultFlow {
        val loginDto = LogInRequestDto(email = email, password = password)
        return responseHandler.safeApiCall { authService.logIn(request = loginDto) }
    }
}
