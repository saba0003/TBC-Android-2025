package com.example.tbc_android_2025.domain.repository

import com.example.tbc_android_2025.domain.common.Resource
import com.example.tbc_android_2025.domain.model.request.LoginRequestModel
import com.example.tbc_android_2025.domain.model.response.LoginResponseModel
import kotlinx.coroutines.flow.Flow

typealias LoginResourceFlow = Flow<Resource<LoginResponseModel>>

interface LoginRepository {

    fun login(request: LoginRequestModel): LoginResourceFlow

}
