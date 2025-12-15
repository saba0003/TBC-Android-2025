package com.example.tbc_android_2025.domain.commons

import com.example.tbc_android_2025.domain.models.MovieModel
import kotlinx.coroutines.flow.Flow

typealias MoviesResourceFlow = Flow<Resource<List<MovieModel>>>
