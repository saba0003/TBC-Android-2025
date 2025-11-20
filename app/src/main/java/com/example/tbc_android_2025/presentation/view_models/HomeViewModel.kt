package com.example.tbc_android_2025.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_android_2025.data.dtos.RemoteUserDto
import com.example.tbc_android_2025.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel : ViewModel() {

    private val _users = MutableStateFlow<List<RemoteUserDto>>(value = emptyList())
    val users: StateFlow<List<RemoteUserDto>> = _users.asStateFlow()

    fun loadUsers() {
        viewModelScope.launch {
            try {
                val response = UserRepository.getUsers(page = 1)
                _users.value = response.data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
