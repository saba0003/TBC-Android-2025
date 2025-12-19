package com.example.tbc_android_2025.domain.use_cases

import com.example.tbc_android_2025.di.qualifiers.RemoteRepository
import com.example.tbc_android_2025.domain.commons.Resource
import com.example.tbc_android_2025.domain.models.MovieModel
import com.example.tbc_android_2025.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias MoviesResourceFlow = Flow<Resource<List<MovieModel>>>

class GetAllMovieModelsUseCase @Inject constructor(@param:RemoteRepository private val repository: MovieRepository) {

    operator fun invoke(): MoviesResourceFlow = repository.getAllMovies()

}
