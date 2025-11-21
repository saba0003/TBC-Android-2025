package com.example.tbc_android_2025.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val userService: FetchService) {

    private val _users = MutableStateFlow<List<User>>(value = emptyList())
    val users: StateFlow<List<User>> = _users.asStateFlow()

    suspend fun getUsers(): List<User> = try {
        val response = userService.getUsers()
        if (!response.isSuccessful) {
            emptyList()
        } else {
            val users = UserMapper.mapListToDomain(dtos = response.body().orEmpty())
            _users.value = users
            users
        }
    } catch (e: Exception) {
        e.printStackTrace()
        emptyList()
    }
}
