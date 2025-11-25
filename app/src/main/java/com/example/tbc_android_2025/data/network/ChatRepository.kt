package com.example.tbc_android_2025.data.network

import com.example.tbc_android_2025.data.extensions.toDomainList
import com.example.tbc_android_2025.domain.models.Chat
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepository @Inject constructor(private val chatService: FetchService) {

    suspend fun getChats(): List<Chat> =
        try {
            val response = chatService.getChats()
            if (response.isSuccessful)
                response.body()?.let { return it.toDomainList() }
            emptyList()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }

}
