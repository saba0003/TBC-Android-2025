package com.example.tbc_android_2025.domain.repository

import androidx.paging.PagingData
import com.example.tbc_android_2025.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

typealias UsersPageFlow = Flow<PagingData<UserModel>>

interface UserRepository {

    fun getUsersPageFromRemote(page: Int): UsersPageFlow

}
