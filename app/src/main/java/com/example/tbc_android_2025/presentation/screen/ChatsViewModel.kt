package com.example.tbc_android_2025.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_android_2025.domain.models.Chat
import com.example.tbc_android_2025.domain.use_cases.GetChatsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor(private val getChatsUseCase: GetChatsUseCase) : ViewModel() {

    private val _chats = MutableStateFlow<List<Chat>>(value = emptyList())
    val chats: StateFlow<List<Chat>> = _chats.asStateFlow()

    fun loadUsers() {
        viewModelScope.launch {
            try {
                val response: List<Chat> = getChatsUseCase()
                _chats.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
