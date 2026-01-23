package com.example.tbc_android_2025.data.remote.service

import com.example.tbc_android_2025.data.remote.dto.ChatDto
import retrofit2.Response
import retrofit2.http.GET

interface ChatFetchService {

    @GET(value = CHATS_ENDPOINT)
    suspend fun getOrders(): Response<List<ChatDto>>

    private companion object {
        const val CHATS_ENDPOINT = "chats"
    }
}
