package com.example.tbc_android_2025.presentation.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_android_2025.data.models.User
import com.example.tbc_android_2025.data.auth.SessionManager
import com.example.tbc_android_2025.data.auth.dtos.responses.*
import com.example.tbc_android_2025.data.repositories.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application = application) {

    private val appContext = application.applicationContext

    fun addUser(user: User) = UserRepository.addUser(user = user)

    fun getUser(email: String, password: String): User? =
        UserRepository.getUser(email = email, password = password)

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
            result.onSuccess { SessionManager.saveToken(context = appContext, token = it.token!!) }
            onResult(result)
        }
    }
}
