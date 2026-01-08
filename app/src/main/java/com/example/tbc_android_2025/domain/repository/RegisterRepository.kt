package com.example.tbc_android_2025.domain.repository

import com.example.tbc_android_2025.domain.common.Resource
import com.example.tbc_android_2025.domain.model.request.RegisterRequestModel
import com.example.tbc_android_2025.domain.model.response.RegisterResponseModel
import kotlinx.coroutines.flow.Flow

typealias RegisterResourceFlow = Flow<Resource<RegisterResponseModel>>

interface RegisterRepository {

    fun register(request: RegisterRequestModel): RegisterResourceFlow

}
