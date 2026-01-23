package com.example.tbc_android_2025.presentation.screen.chats

import com.example.tbc_android_2025.domain.error.AppError
import com.example.tbc_android_2025.presentation.model.ChatModel

interface ChatsContract {
    data class State(
        val chats: List<ChatModel> = emptyList(),
        val searchQuery: String = "",
        val isLoading: Boolean = NOT_YET_STARTED
    ) {
        val filteredChats: List<ChatModel>
            get() = if (searchQuery.isEmpty()) chats
            else chats.filter { it.owner.contains(other = searchQuery, ignoreCase = true) }

        companion object {
            private const val NOT_YET_STARTED = false
            private const val LOADING = true

            fun loading() = State(isLoading = LOADING)
        }
    }

    sealed interface Event {
        data object OnFetchChats : Event
        data class OnSearch(val query: String) : Event
    }

    sealed interface SideEffect {
        data class ShowError(val error: AppError) : SideEffect
    }
}
