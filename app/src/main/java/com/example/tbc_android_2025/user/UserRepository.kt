package com.example.tbc_android_2025.user

import com.example.tbc_android_2025.api.RetrofitInstance
import com.example.tbc_android_2025.api.requests.*
import com.example.tbc_android_2025.api.responses.*
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
    ): Result<RegisterResponse> =
        withContext(context = Dispatchers.IO) {
            try {
                val response =
                    RetrofitInstance.api.register(
                        request = RegisterRequest(
                            email = email,
                            password = password
                        )
                    )
                Result.success(value = response)
            } catch (e: Exception) {
                Result.failure(exception = e)
            }
        }

    suspend fun loginUserRemote(email: String, password: String): Result<LoginResponse> =
        withContext(context = Dispatchers.IO) {
            try {
                val response = RetrofitInstance.api.login(
                    request = LoginRequest(
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
