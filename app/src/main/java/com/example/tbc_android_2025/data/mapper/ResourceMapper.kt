package com.example.tbc_android_2025.data.mapper

import com.example.tbc_android_2025.domain.common.Resource
import com.example.tbc_android_2025.domain.common.Resource.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <DTO, DOMAIN> Flow<Resource<DTO>>.asResource(onSuccess: (DTO) -> DOMAIN): Flow<Resource<DOMAIN>> =
    map {
        when (it) {
            is Success -> Success(data = onSuccess(it.data))
            is Error -> Error(errorCode = it.errorCode, throwable = it.throwable)
            is Loader -> Loader(isLoading = it.isLoading)
        }
    }
