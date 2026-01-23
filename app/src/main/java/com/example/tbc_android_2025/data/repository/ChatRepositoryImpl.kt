package com.example.tbc_android_2025.data.repository

import com.example.tbc_android_2025.data.remote.common.ResponseHandler
import com.example.tbc_android_2025.data.remote.mapper.asResource
import com.example.tbc_android_2025.data.remote.mapper.toDomain
import com.example.tbc_android_2025.data.remote.service.ChatFetchService
import com.example.tbc_android_2025.domain.repository.ChatRepository
import com.example.tbc_android_2025.domain.repository.ChatsListResourceFlow
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val chatFetchService: ChatFetchService, private val responseHandler: ResponseHandler
) : ChatRepository {

    override fun getChats(): ChatsListResourceFlow =
        responseHandler.safeApiCall { chatFetchService.getOrders() }.asResource { it.toDomain() }

}
