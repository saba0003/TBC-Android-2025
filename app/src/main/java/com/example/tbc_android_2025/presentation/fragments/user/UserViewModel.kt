package com.example.tbc_android_2025.presentation.fragments.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_android_2025.data.auth.SessionManager
import com.example.tbc_android_2025.data.auth.dtos.responses.*
import com.example.tbc_android_2025.data.repositories.UserRepository
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    fun addUser(user: User) = UserRepository.addUser(user = user)

    fun getUser(username: String, password: String): User? =
        UserRepository.getUser(username = username, password = password)

    fun registerUserRemote(
        email: String,
        password: String,
        onResult: (Result<RegisterResponseDto>) -> Unit
    ) {
        viewModelScope.launch {
            val result = UserRepository.registerUserRemote(email = email, password = password)
            onResult(result)
        }
    }

    fun loginUserRemote(
        email: String,
        password: String,
        onResult: (Result<LoginResponseDto>) -> Unit
    ) {
        viewModelScope.launch {
            val result = UserRepository.loginUserRemote(email = email, password = password)
            result.onSuccess { SessionManager.authToken = it.token }
            onResult(result)
        }
    }
}
