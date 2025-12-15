package com.example.tbc_android_2025.domain.use_cases

import com.example.tbc_android_2025.di.qualifiers.RemoteRepository
import com.example.tbc_android_2025.domain.commons.MoviesResourceFlow
import com.example.tbc_android_2025.domain.commons.Resource
import com.example.tbc_android_2025.domain.exceptions.AppError.SearchQuery
import com.example.tbc_android_2025.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetMovieModelsByTitleUseCase @Inject constructor(@param:RemoteRepository private val repository: MovieRepository) {

    operator fun invoke(title: String): MoviesResourceFlow {
        val trimmedTitle = title.trim()
        return when {
            trimmedTitle.length < 3 -> flowOf(value = Resource.Error(error = SearchQuery))
            else -> repository.getMoviesByTitle(title = trimmedTitle)
        }
    }

}
