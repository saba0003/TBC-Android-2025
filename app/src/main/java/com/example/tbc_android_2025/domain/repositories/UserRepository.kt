package com.example.tbc_android_2025.domain.repositories

import com.example.tbc_android_2025.domain.commons.Resource
import com.example.tbc_android_2025.domain.models.responses.UsersPage
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUsers(page: Int): Flow<Resource<UsersPage>>

}
