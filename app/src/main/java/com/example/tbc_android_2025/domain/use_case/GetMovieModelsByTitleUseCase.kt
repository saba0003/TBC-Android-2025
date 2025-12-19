package com.example.tbc_android_2025.domain.use_case

import com.example.tbc_android_2025.di.qualifier.RemoteRepository
import com.example.tbc_android_2025.domain.common.Resource
import com.example.tbc_android_2025.domain.exception.AppError.SearchQuery
import com.example.tbc_android_2025.domain.repository.MovieRepository
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetMovieModelsByTitleUseCase @Inject constructor(@param:RemoteRepository private val repository: MovieRepository) {

    operator fun invoke(title: String): MoviesResourceFlow {
        val trimmedTitle = title.trim()
        return if (!validateTitle(title = trimmedTitle))
            flowOf(value = Resource.Error(error = SearchQuery))
        else
            repository.getMoviesByTitle(title = trimmedTitle)
    }


    /** ========================================== AUX ========================================== */
    private fun validateTitle(title: String) = title.length > 2
    /** ========================================================================================= */
}
