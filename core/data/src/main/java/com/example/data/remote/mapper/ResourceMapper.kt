package com.example.data.remote.mapper

import com.example.domain.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <DTO, DOMAIN> Flow<Resource<DTO>>.asResource(onSuccess: (DTO) -> DOMAIN): Flow<Resource<DOMAIN>> =
    map {
        when (it) {
            is Resource.Success -> Resource.Success(data = onSuccess(it.data))
            is Resource.Error -> Resource.Error(value = it.value, throwable = it.throwable)
            is Resource.Loader -> Resource.Loader(isLoading = it.isLoading)
        }
    }
