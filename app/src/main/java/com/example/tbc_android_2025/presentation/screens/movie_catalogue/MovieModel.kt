package com.example.tbc_android_2025.presentation.screens.movie_catalogue

data class MovieModel(
    val id: Int,
    val title: String,
    val description: String,
    val releaseDate: String,
    val duration: String,
    val genres: List<String>,
    val languages: List<String>,
    val ageRating: String,
    val director: String,
    val country: String,
    val postersUrls: List<String>,
    val trailersUrls: List<String>,
    val budget: String,
    val boxOfficeGross: String,
)
