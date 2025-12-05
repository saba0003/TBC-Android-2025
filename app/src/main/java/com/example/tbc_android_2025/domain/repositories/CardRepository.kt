package com.example.tbc_android_2025.domain.repositories

import com.example.tbc_android_2025.domain.commons.Resource
import com.example.tbc_android_2025.domain.models.CardModel
import kotlinx.coroutines.flow.Flow

interface CardRepository {

    fun getCards() : Flow<Resource<List<CardModel>>>

}
