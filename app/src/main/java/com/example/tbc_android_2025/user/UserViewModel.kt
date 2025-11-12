package com.example.tbc_android_2025.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_android_2025.api.responses.LoginResponse
import com.example.tbc_android_2025.api.responses.RegisterResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    fun addUser(user: User) = UserRepository.addUser(user = user)

    fun getUser(username: String, password: String): User? =
        UserRepository.getUser(username = username, password = password)

    fun registerUserRemote(
        email: String,
        password: String,
        onResult: (Result<RegisterResponse>) -> Unit
    ) {
        viewModelScope.launch {
            val result = UserRepository.registerUserRemote(email = email, password = password)
            onResult(result)
        }
    }

    fun loginUserRemote(
        email: String,
        password: String,
        onResult: (Result<LoginResponse>) -> Unit
    ) {
        viewModelScope.launch {
            val result = UserRepository.loginUserRemote(email = email, password = password)
            onResult(result)
        }
    }
}
