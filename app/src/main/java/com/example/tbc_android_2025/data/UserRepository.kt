package com.example.tbc_android_2025.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val userService: FetchService) {

    suspend fun getUsers(): List<User> =
        try {
            val response = userService.getUsers()
            if (response.isSuccessful)
                UserMapper.mapListToDomain(dtos = response.body().orEmpty())
            else
                emptyList()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
}
