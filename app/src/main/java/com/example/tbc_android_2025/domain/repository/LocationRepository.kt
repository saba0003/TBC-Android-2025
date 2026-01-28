package com.example.tbc_android_2025.domain.repository

import com.example.tbc_android_2025.domain.common.Resource
import com.example.tbc_android_2025.domain.model.LocationModel
import kotlinx.coroutines.flow.Flow

typealias LocationsResourceFlow = Flow<Resource<List<LocationModel>>>

interface LocationRepository {
    fun getLocations(): LocationsResourceFlow
}
