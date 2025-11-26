package com.example.tbc_android_2025.data.network

import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepository @Inject constructor(private val chatService: ChatService) {

    suspend fun getChats(): Response<List<ChatResponseDto>> = chatService.getChats()

}
