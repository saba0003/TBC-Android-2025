package com.example.tbc_android_2025.domain.common

import com.example.tbc_android_2025.domain.error.AppError

sealed class Resource<out D> {
    data class Success<out D>(val data: D) : Resource<D>()
    data class Error(val errorCode: AppError, val throwable: Throwable? = null) : Resource<Nothing>()
    data class Loader(val isLoading: Boolean) : Resource<Nothing>()
}
