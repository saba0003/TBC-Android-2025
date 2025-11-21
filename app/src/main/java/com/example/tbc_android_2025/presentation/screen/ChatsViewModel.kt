package com.example.tbc_android_2025.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_android_2025.data.User
import com.example.tbc_android_2025.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val _users = MutableStateFlow<List<User>>(value = emptyList())
    val users: StateFlow<List<User>> = _users.asStateFlow()

    fun loadUsers() {
        viewModelScope.launch {
            try {
                val response = userRepository.getUsers()
                _users.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
