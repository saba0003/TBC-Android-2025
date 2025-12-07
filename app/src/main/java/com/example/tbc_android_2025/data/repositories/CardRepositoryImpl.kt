package com.example.tbc_android_2025.data.repositories

import com.example.tbc_android_2025.data.remote.commons.ResponseHandler
import com.example.tbc_android_2025.data.mappers.asResource
import com.example.tbc_android_2025.data.mappers.toDomain
import com.example.tbc_android_2025.data.remote.services.FetchService
import com.example.tbc_android_2025.domain.commons.Resource
import com.example.tbc_android_2025.domain.models.CardModel
import com.example.tbc_android_2025.domain.repositories.CardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CardRepositoryImpl @Inject constructor(
    private val responseHandler: ResponseHandler,
    private val fetchService: FetchService
) : CardRepository {

    override fun getCards(): Flow<Resource<List<CardModel>>> =
        responseHandler.safeApiCall { fetchService.getCards() }.asResource { it.toDomain() }

}
