package com.example.tbc_android_2025.data.remote

import com.example.tbc_android_2025.data.remote.commons.ResponseHandler
import com.example.tbc_android_2025.data.remote.dtos.UserResponseDto
import com.example.tbc_android_2025.data.remote.services.RemoteFetchService
import com.example.tbc_android_2025.domain.commons.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteUserDataSource @Inject constructor(
    private val remoteFetchService: RemoteFetchService,
    private val handler: ResponseHandler
) {

    fun getUsers(): Flow<Resource<List<UserResponseDto>>> =
        handler.safeApiCall { remoteFetchService.getUsers() }

}
