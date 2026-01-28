package com.example.tbc_android_2025.domain.use_case.remote

import com.example.tbc_android_2025.domain.repository.LocationRepository
import com.example.tbc_android_2025.domain.repository.LocationsResourceFlow
import javax.inject.Inject

class GetLocationsUseCase @Inject constructor(private val locationRepository: LocationRepository) {

    operator fun invoke(): LocationsResourceFlow = locationRepository.getLocations()

}
