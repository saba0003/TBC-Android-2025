package com.example.tbc_android_2025.data.network

import com.example.tbc_android_2025.data.network.NetworkConstants.CHATS_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET

interface ChatService {

    @GET(value = CHATS_ENDPOINT)
    suspend fun getChats(): Response<List<ChatResponseDto>>

}
