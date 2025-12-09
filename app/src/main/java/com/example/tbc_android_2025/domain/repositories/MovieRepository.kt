package com.example.tbc_android_2025.domain.repositories

import com.example.tbc_android_2025.domain.commons.Resource
import com.example.tbc_android_2025.domain.models.MovieModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovies(): Flow<Resource<List<MovieModel>>>

}
