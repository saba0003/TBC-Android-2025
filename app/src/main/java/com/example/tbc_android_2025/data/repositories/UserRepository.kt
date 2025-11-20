package com.example.tbc_android_2025.data.repositories

import com.example.tbc_android_2025.data.HttpClient
import com.example.tbc_android_2025.data.auth.log_in.dtos.request.LogInRequestDto
import com.example.tbc_android_2025.data.auth.register.dtos.request.RegisterRequestDto
import com.example.tbc_android_2025.data.auth.log_in.dtos.respsonse.LogInResponseDto
import com.example.tbc_android_2025.data.auth.register.dtos.response.RegisterResponseDto
import com.example.tbc_android_2025.data.dtos.UsersResponseDto
import com.example.tbc_android_2025.data.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// TODO: divide into remote and local repositories
object UserRepository {

    private val _users = mutableListOf<User>()


    fun addUser(user: User) = _users.add(element = user)

    fun getUser(email: String, password: String): User? =
        _users.find { it.email == email && it.password == password }


    suspend fun getUsers(page: Int): UsersResponseDto = HttpClient.api.getUsers(page = page)

    suspend fun registerUserRemote(email: String, password: String): Result<RegisterResponseDto> =
        withContext(context = Dispatchers.IO) {
            try {
                val response =
                    HttpClient.authApi.register(
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

    suspend fun loginUserRemote(email: String, password: String): Result<LogInResponseDto> =
        withContext(context = Dispatchers.IO) {
            try {
                val response = HttpClient.authApi.login(
                    request = LogInRequestDto(
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
