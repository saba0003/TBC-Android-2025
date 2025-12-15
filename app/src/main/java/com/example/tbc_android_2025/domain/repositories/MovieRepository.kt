package com.example.tbc_android_2025.domain.repositories

import com.example.tbc_android_2025.domain.commons.MoviesResourceFlow

interface MovieRepository {
    fun getAllMovies(): MoviesResourceFlow
    fun getMoviesByTitle(title: String): MoviesResourceFlow
}
