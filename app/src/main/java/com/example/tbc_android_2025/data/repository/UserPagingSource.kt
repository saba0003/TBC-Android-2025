package com.example.tbc_android_2025.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tbc_android_2025.data.remote.mapper.toDomain
import com.example.tbc_android_2025.data.remote.service.FetchService
import com.example.tbc_android_2025.domain.error.AppError
import com.example.tbc_android_2025.domain.model.UserModel

class UserPagingSource(private val fetchService: FetchService) : PagingSource<Int, UserModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserModel> {
        return try {
            val currentPage = params.key ?: 1

            val response = fetchService.getUsersPage(page = currentPage)

            // 2. Handle the Retrofit Response
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    LoadResult.Page(
                        data = body.data.toDomain(),
                        prevKey = if (currentPage == 1) null else currentPage.dec(),
                        nextKey = if (currentPage >= body.totalPages) null else currentPage.inc()
                    )
                } else {
                    LoadResult.Error(throwable = AppError.Technical(message = "Empty response body"))
                }
            } else {
                // Handle API error codes (404, 500, etc.)
                LoadResult.Error(throwable = AppError.Technical(message = "Server error: ${response.code()}"))
            }
        } catch (e: Exception) {
            // This catches Network errors AND Moshi parsing errors!
            LoadResult.Error(throwable = AppError.Technical(message = e.message ?: "Unknown Error"))
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UserModel>): Int? =
        state.anchorPosition?.let {
            state.closestPageToPosition(anchorPosition = it)?.prevKey?.inc()
                ?: state.closestPageToPosition(anchorPosition = it)?.nextKey?.dec()
        }
}
