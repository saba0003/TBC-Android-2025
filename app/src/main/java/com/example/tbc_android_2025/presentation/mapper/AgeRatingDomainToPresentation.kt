package com.example.tbc_android_2025.presentation.mapper

import com.example.tbc_android_2025.presentation.screen.model.MovieModel.AgeRating as AgeRatingPresentation
import com.example.tbc_android_2025.domain.model.MovieModel.AgeRating as AgeRatingDomain

fun AgeRatingDomain.toPresentation() = AgeRatingPresentation.valueOf(value = name)
