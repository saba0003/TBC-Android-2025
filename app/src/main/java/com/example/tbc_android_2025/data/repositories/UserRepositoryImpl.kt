package com.example.tbc_android_2025.data.repositories

import com.example.tbc_android_2025.data.commons.ResponseHandler
import com.example.tbc_android_2025.data.mappers.asResource
import com.example.tbc_android_2025.data.mappers.toDomain
import com.example.tbc_android_2025.data.services.FetchService
import com.example.tbc_android_2025.domain.commons.Resource
import com.example.tbc_android_2025.domain.models.UsersModel
import com.example.tbc_android_2025.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val fetchService: FetchService,
    private val responseHandler: ResponseHandler
) : UserRepository {

    override fun getUsers(): Flow<Resource<UsersModel>> =
        responseHandler.safeApiCall { fetchService.getUsers() }
            .asResource { it.toDomain() }

}
