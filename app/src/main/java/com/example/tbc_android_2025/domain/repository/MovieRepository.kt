package com.example.tbc_android_2025.domain.repository

import com.example.tbc_android_2025.domain.use_case.MoviesResourceFlow

interface MovieRepository {
    fun getAllMovies(): MoviesResourceFlow
    fun getMoviesByTitle(title: String): MoviesResourceFlow
}
