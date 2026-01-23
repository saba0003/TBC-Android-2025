package com.example.tbc_android_2025.domain.repository

import com.example.tbc_android_2025.domain.common.Resource
import com.example.tbc_android_2025.domain.model.ChatModel
import kotlinx.coroutines.flow.Flow

typealias ChatsListResourceFlow = Flow<Resource<List<ChatModel>>>

interface ChatRepository {
    fun getChats(): ChatsListResourceFlow
}
