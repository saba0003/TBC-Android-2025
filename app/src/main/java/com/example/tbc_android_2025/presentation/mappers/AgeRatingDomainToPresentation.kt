package com.example.tbc_android_2025.presentation.mappers

import com.example.tbc_android_2025.presentation.screens.movie_catalogue.MovieModel.AgeRating as AgeRatingPresentation
import com.example.tbc_android_2025.domain.models.MovieModel.AgeRating as AgeRatingDomain

fun AgeRatingDomain.toPresentation() = AgeRatingPresentation.valueOf(value = name)
