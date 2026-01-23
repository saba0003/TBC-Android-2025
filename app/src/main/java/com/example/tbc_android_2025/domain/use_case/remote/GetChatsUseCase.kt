package com.example.tbc_android_2025.domain.use_case.remote

import com.example.tbc_android_2025.domain.repository.ChatRepository
import com.example.tbc_android_2025.domain.repository.ChatsListResourceFlow
import javax.inject.Inject

class GetChatsUseCase @Inject constructor(private val chatRepository: ChatRepository) {

    operator fun invoke(): ChatsListResourceFlow = chatRepository.getChats()

}
