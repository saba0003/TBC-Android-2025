package com.example.tbc_android_2025.domain.use_cases

import com.example.tbc_android_2025.data.network.ChatRepository
import com.example.tbc_android_2025.domain.models.Chat
import javax.inject.Inject

class GetChatsUseCase @Inject constructor(private val chatRepository: ChatRepository) {

    suspend operator fun invoke(): List<Chat> = chatRepository.getChats()

}
