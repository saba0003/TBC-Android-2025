package com.example.tbc_android_2025.data.mapper

import com.example.tbc_android_2025.data.remote.dto.MoviesResponseDto
import com.example.tbc_android_2025.data.remote.dto.MoviesResponseDto.MovieDto
import com.example.tbc_android_2025.domain.mapper.*
import com.example.tbc_android_2025.domain.model.MovieModel

fun MovieDto.toDomain() =
    MovieModel(
        id = id,
        imdbId = imdbId,
        title = title,
        description = description,
        releaseDate = releaseDate.toLocalDate(),
        duration = duration,
        genres = genres.toGenres(),
        languages = languages.toLanguages(),
        ageRating = ageRating.toAgeRating(),
        director = director,
        country = country,
        postersUrls = postersUrls,
        trailersUrls = trailersUrls,
        budget = budget,
        boxOfficeGross = boxOfficeGross
    )

fun MoviesResponseDto.toDomain() = data.map { it.toDomain() }
