package com.example.tbc_android_2025.domain.use_case

import com.example.tbc_android_2025.di.qualifier.RemoteRepository
import com.example.tbc_android_2025.domain.common.Resource
import com.example.tbc_android_2025.domain.model.MovieModel
import com.example.tbc_android_2025.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias MoviesResourceFlow = Flow<Resource<List<MovieModel>>>

class GetAllMovieModelsUseCase @Inject constructor(@param:RemoteRepository private val repository: MovieRepository) {

    operator fun invoke(): MoviesResourceFlow = repository.getAllMovies()

}
