package com.example.tbc_android_2025.data.repository

import com.example.tbc_android_2025.data.remote.common.ResponseHandler
import com.example.tbc_android_2025.data.remote.mapper.asResource
import com.example.tbc_android_2025.data.remote.mapper.request.toData
import com.example.tbc_android_2025.data.remote.mapper.response.toDomain
import com.example.tbc_android_2025.data.remote.service.LoginService
import com.example.tbc_android_2025.domain.model.request.LoginRequestModel
import com.example.tbc_android_2025.domain.repository.LoginRepository
import com.example.tbc_android_2025.domain.repository.LoginResourceFlow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginService: LoginService,
    private val responseHandler: ResponseHandler
) : LoginRepository {

    override fun login(request: LoginRequestModel): LoginResourceFlow =
        responseHandler.safeApiCall { loginService.login(request = request.toData()) }
            .asResource { it.toDomain() }

}
