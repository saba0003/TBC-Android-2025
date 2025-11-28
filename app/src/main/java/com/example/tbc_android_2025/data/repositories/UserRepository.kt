package com.example.tbc_android_2025.data.repositories

import com.example.tbc_android_2025.data.commons.ResponseHandler
import com.example.tbc_android_2025.data.services.FetchService
import com.example.tbc_android_2025.domain.commons.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val fetchService: FetchService,
    private val responseHandler: ResponseHandler
) {


}
