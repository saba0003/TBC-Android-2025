package com.example.tbc_android_2025.data.remote.service

import com.example.tbc_android_2025.data.remote.dto.LocationDto
import retrofit2.Response
import retrofit2.http.GET

interface LocationFetchService {

    @GET(value = ENDPOINT)
    suspend fun getLocations(): Response<List<LocationDto>>

    private companion object {
        const val ENDPOINT = "cards"
    }
}
