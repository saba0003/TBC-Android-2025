package com.example.tbc_android_2025.data.repository

import com.example.tbc_android_2025.data.remote.common.ResponseHandler
import com.example.tbc_android_2025.data.remote.mapper.asResource
import com.example.tbc_android_2025.data.remote.mapper.toDomain
import com.example.tbc_android_2025.data.remote.service.LocationFetchService
import com.example.tbc_android_2025.domain.repository.LocationRepository
import com.example.tbc_android_2025.domain.repository.LocationsResourceFlow
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val locationFetchService: LocationFetchService,
    private val responseHandler: ResponseHandler
) : LocationRepository {

    override fun getLocations(): LocationsResourceFlow =
        responseHandler.safeApiCall { locationFetchService.getLocations() }
            .asResource { it.toDomain() }

}
