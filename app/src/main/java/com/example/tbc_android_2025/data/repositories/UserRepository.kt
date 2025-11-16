package com.example.tbc_android_2025.data.repositories

import com.example.tbc_android_2025.data.HttpClient
import com.example.tbc_android_2025.data.auth.dtos.requests.LoginRequestDto
import com.example.tbc_android_2025.data.auth.dtos.requests.RegisterRequestDto
import com.example.tbc_android_2025.data.auth.dtos.responses.LoginResponseDto
import com.example.tbc_android_2025.data.auth.dtos.responses.RegisterResponseDto
import com.example.tbc_android_2025.presentation.fragments.user.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object UserRepository {

    private val _users = mutableListOf<User>()


    fun addUser(user: User) = _users.add(element = user)

    fun getUser(username: String, password: String): User? =
        _users.find { it.username == username && it.password == password }


    suspend fun registerUserRemote(
        email: String,
        password: String
    ): Result<RegisterResponseDto> =
        withContext(context = Dispatchers.IO) {
            try {
                val response =
                    HttpClient.api.register(
                        request = RegisterRequestDto(
                            email = email,
                            password = password
                        )
                    )
                Result.success(value = response)
            } catch (e: Exception) {
                Result.failure(exception = e)
            }
        }

    suspend fun loginUserRemote(email: String, password: String): Result<LoginResponseDto> =
        withContext(context = Dispatchers.IO) {
            try {
                val response = HttpClient.api.login(
                    request = LoginRequestDto(
                        email = email,
                        password = password
                    )
                )
                Result.success(value = response)
            } catch (e: Exception) {
                Result.failure(exception = e)
            }
        }
}
