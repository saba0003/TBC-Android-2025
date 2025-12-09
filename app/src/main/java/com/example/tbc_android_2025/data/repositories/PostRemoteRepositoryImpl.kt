package com.example.tbc_android_2025.data.repositories

import com.example.tbc_android_2025.data.commons.ResponseHandler
import com.example.tbc_android_2025.data.mappers.asResource
import com.example.tbc_android_2025.data.mappers.toDomain
import com.example.tbc_android_2025.data.remote.services.FetchService
import com.example.tbc_android_2025.di.qualifiers.RemoteRepository
import com.example.tbc_android_2025.domain.commons.Resource
import com.example.tbc_android_2025.domain.models.PostModel
import com.example.tbc_android_2025.domain.repositories.PostRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@RemoteRepository
class PostRemoteRepositoryImpl @Inject constructor(
    private val fetchService: FetchService,
    private val responseHandler: ResponseHandler
) : PostRepository {

    override fun getPostModels():  Flow<Resource<List<PostModel>>> =
        responseHandler.safeApiCall { fetchService.getPosts() }
            .asResource { it.toDomain() }

}
