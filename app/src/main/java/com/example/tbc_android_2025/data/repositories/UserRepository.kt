package com.example.tbc_android_2025.data.repositories

import com.example.tbc_android_2025.data.auth.FetchService
import com.example.tbc_android_2025.data.auth.LoginService
import com.example.tbc_android_2025.data.auth.RegisterService
import com.example.tbc_android_2025.data.auth.dtos.requests.LoginRequestDto
import com.example.tbc_android_2025.data.auth.dtos.requests.RegisterRequestDto
import com.example.tbc_android_2025.data.auth.dtos.responses.LoginResponseDto
import com.example.tbc_android_2025.data.auth.dtos.responses.RegisterResponseDto
import com.example.tbc_android_2025.data.dtos.UsersResponseDto
import com.example.tbc_android_2025.data.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

// TODO: divide into remote and local repositories
@Singleton
class UserRepository @Inject constructor(
    private val fetchService: FetchService,
    private val registerService: RegisterService,
    private val loginService: LoginService
) {

    private val _users = mutableListOf<User>()


    fun addUser(user: User) = _users.add(element = user)

    fun getUser(email: String, password: String): User? =
        _users.find { it.email == email && it.password == password }


    suspend fun getUsers(page: Int): UsersResponseDto = fetchService.getUsers(page = page)

    suspend fun registerUserRemote(
        email: String,
        password: String
    ): Result<RegisterResponseDto> = withContext(context = Dispatchers.IO) {
        try {
            val response = registerService.register(
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

    suspend fun loginUserRemote(
        email: String,
        password: String
    ): Result<LoginResponseDto> = withContext(context = Dispatchers.IO) {
        try {
            val response = loginService.logIn(
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
