package com.example.tbc_android_2025.data.local.data_sources

import com.example.tbc_android_2025.data.local.users.UserDao
import com.example.tbc_android_2025.data.local.users.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalUserDataSource @Inject constructor(private val userDao: UserDao) {
    fun getUsers(): Flow<List<UserEntity>> = userDao.getAllUsers()
    suspend fun saveUsers(users: List<UserEntity>) = userDao.insertAll(users)
    suspend fun clearUsers() = userDao.clearUsers()
}
