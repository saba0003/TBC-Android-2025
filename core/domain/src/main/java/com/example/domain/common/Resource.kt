package com.example.domain.common

import com.example.domain.error.AppError

sealed class Resource<out D> {
    data class Success<out D>(val data: D) : Resource<D>()
    data class Error(val value: AppError, val throwable: Throwable? = null) : Resource<Nothing>()
    data class Loader(val isLoading: Boolean) : Resource<Nothing>() {
        companion object {
            private const val LOADING = true
            private const val FINISHED = false

            fun loading() = Loader(isLoading = LOADING)
            fun finished() = Loader(isLoading = FINISHED)
        }
    }
}
