package com.example.tbc_android_2025.viewmodels


import androidx.lifecycle.ViewModel
import com.example.tbc_android_2025.message.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


typealias Messages = List<Message>
typealias MessagesStateFlow = StateFlow<Messages>


class ChatViewModel : ViewModel() {

    private val _messages = MutableStateFlow<Messages>(value = emptyList())
    val messages: MessagesStateFlow = _messages

    fun sendMessage(text: String) {
        val newMessage = Message(content = text)
        _messages.value += newMessage
    }
}
