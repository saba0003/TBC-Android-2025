package com.example.tbc_android_2025.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoviesResponseDto(
    val data: List<MovieDto>,
    val message: String,
    val status: Int,
    val timestamp: String
) {
    @JsonClass(generateAdapter = true)
    data class MovieDto(
        val id: Int,
        val imdbId: String,
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
        val createdOn: String,
        val updatedOn: String
    )
}
