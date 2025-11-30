package com.example.tbc_android_2025.data.repositories

import com.example.tbc_android_2025.data.commons.ResponseHandler
import com.example.tbc_android_2025.data.mappers.asResource
import com.example.tbc_android_2025.data.mappers.toDomain
import com.example.tbc_android_2025.data.services.FetchService
import com.example.tbc_android_2025.domain.commons.Resource
import com.example.tbc_android_2025.domain.models.responses.UsersPage
import com.example.tbc_android_2025.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val fetchService: FetchService,
    private val responseHandler: ResponseHandler
) : UserRepository {

    override fun getUsers(page: Int): Flow<Resource<UsersPage>> =
        responseHandler.safeApiCall { fetchService.getUsers(page = page) }
            .asResource { it.toDomain() }

}
