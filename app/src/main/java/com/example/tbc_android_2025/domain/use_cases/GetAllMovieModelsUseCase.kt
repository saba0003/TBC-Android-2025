package com.example.tbc_android_2025.domain.use_cases

import com.example.tbc_android_2025.di.qualifiers.RemoteRepository
import com.example.tbc_android_2025.domain.commons.MoviesResourceFlow
import com.example.tbc_android_2025.domain.repositories.MovieRepository
import javax.inject.Inject

class GetAllMovieModelsUseCase @Inject constructor(@param:RemoteRepository private val repository: MovieRepository) {

    operator fun invoke(): MoviesResourceFlow = repository.getAllMovies()

}
