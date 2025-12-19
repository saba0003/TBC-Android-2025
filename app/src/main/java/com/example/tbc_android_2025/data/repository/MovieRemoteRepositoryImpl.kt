package com.example.tbc_android_2025.data.repository

import com.example.tbc_android_2025.data.common.ResponseHandler
import com.example.tbc_android_2025.data.mapper.asResource
import com.example.tbc_android_2025.data.mapper.toDomain
import com.example.tbc_android_2025.data.remote.service.FetchService
import com.example.tbc_android_2025.di.qualifier.RemoteRepository
import com.example.tbc_android_2025.domain.repository.MovieRepository
import com.example.tbc_android_2025.domain.use_case.MoviesResourceFlow
import javax.inject.Inject

@RemoteRepository
class MovieRemoteRepositoryImpl @Inject constructor(
    private val fetchService: FetchService,
    private val responseHandler: ResponseHandler
) : MovieRepository {

    override fun getAllMovies(): MoviesResourceFlow =
        responseHandler.safeApiCall { fetchService.getAllMovies() }
            .asResource { it.toDomain() }

    override fun getMoviesByTitle(title: String): MoviesResourceFlow =
        responseHandler.safeApiCall { fetchService.getMoviesByTitle(title = title) }
            .asResource { it.toDomain() }

}
