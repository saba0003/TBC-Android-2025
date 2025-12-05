package com.example.tbc_android_2025.domain.use_cases

import com.example.tbc_android_2025.domain.commons.Resource
import com.example.tbc_android_2025.domain.models.CardModel
import com.example.tbc_android_2025.domain.repositories.CardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCardsUseCase @Inject constructor(private val cardsRepository: CardRepository) {

    operator fun invoke(): Flow<Resource<List<CardModel>>> = cardsRepository.getCards()

}