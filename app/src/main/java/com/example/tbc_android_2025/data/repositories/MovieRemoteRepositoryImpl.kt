package com.example.tbc_android_2025.data.repositories

import com.example.tbc_android_2025.data.commons.ResponseHandler
import com.example.tbc_android_2025.data.mappers.asResource
import com.example.tbc_android_2025.data.mappers.toDomain
import com.example.tbc_android_2025.data.remote.services.FetchService
import com.example.tbc_android_2025.di.qualifiers.RemoteRepository
import com.example.tbc_android_2025.domain.commons.Resource
import com.example.tbc_android_2025.domain.models.MovieModel
import com.example.tbc_android_2025.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@RemoteRepository
class MovieRemoteRepositoryImpl @Inject constructor(
    private val fetchService: FetchService,
    private val responseHandler: ResponseHandler
) : MovieRepository {

    override fun getMovies():  Flow<Resource<List<MovieModel>>> =
        responseHandler.safeApiCall { fetchService.getMovies() }
            .asResource { it.toDomain() }

}
