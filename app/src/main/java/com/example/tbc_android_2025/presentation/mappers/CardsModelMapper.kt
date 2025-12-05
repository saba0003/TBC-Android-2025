package com.example.tbc_android_2025.presentation.mappers

import com.example.tbc_android_2025.domain.models.CardModel
import com.example.tbc_android_2025.presentation.screen.cards.Card

fun CardModel.toPresentation() =
    Card(
        location = location,
        altitude = altitude,
        title = title,
        image = image,
        stars = stars
    )

fun List<CardModel>.toPresentation() = map { it.toPresentation() }
