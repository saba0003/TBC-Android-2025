package com.example.tbc_android_2025.presentation.screen.cards

import com.example.tbc_android_2025.domain.models.CardModel

data class CardsState(
    val data: List<CardModel> = emptyList(),
    val error: String? = null,
    val isLoading: Boolean = false
)
