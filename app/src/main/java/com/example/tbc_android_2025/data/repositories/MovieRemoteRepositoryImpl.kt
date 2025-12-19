package com.example.tbc_android_2025.data.repositories

import com.example.tbc_android_2025.data.commons.ResponseHandler
import com.example.tbc_android_2025.data.mappers.asResource
import com.example.tbc_android_2025.data.mappers.toDomain
import com.example.tbc_android_2025.data.remote.services.FetchService
import com.example.tbc_android_2025.di.qualifiers.RemoteRepository
import com.example.tbc_android_2025.domain.repositories.MovieRepository
import com.example.tbc_android_2025.domain.use_cases.MoviesResourceFlow
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
