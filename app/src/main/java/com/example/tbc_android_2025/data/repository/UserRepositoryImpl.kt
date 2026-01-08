package com.example.tbc_android_2025.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.tbc_android_2025.data.remote.service.FetchService
import com.example.tbc_android_2025.domain.repository.UserRepository
import com.example.tbc_android_2025.domain.repository.UsersPageFlow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val fetchService: FetchService) :
    UserRepository {

    override fun getUsersPageFromRemote(page: Int): UsersPageFlow =
        Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = DO_NOT_ENABLE_PLACEHOLDERS
            ),
            pagingSourceFactory = {
                UserPagingSource(fetchService = fetchService)
            }
        ).flow

    private companion object {
        const val PAGE_SIZE = 6
        const val DO_NOT_ENABLE_PLACEHOLDERS = false
    }
}
