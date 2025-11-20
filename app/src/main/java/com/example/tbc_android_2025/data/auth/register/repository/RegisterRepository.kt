package com.example.tbc_android_2025.data.auth.register.repository

import com.example.tbc_android_2025.data.auth.register.dtos.request.RegisterRequestDto
import com.example.tbc_android_2025.data.auth.register.dtos.response.RegisterResponseDto
import com.example.tbc_android_2025.data.auth.register.service.RegisterService
import com.example.tbc_android_2025.data.auth.commons.ResponseHandler
import com.example.tbc_android_2025.data.auth.commons.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias RegisterResultFlow = Flow<Resource<RegisterResponseDto>>

class RegisterRepository @Inject constructor(
    private val authService: RegisterService,
    private val responseHandler: ResponseHandler
) {
    fun login(email: String, password: String): RegisterResultFlow {
        val registerDto = RegisterRequestDto(email = email, password = password)
        return responseHandler.safeApiCall { authService.register(request = registerDto) }
    }
}
