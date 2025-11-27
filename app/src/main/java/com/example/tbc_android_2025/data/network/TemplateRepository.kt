package com.example.tbc_android_2025.data.network

import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TemplateRepository @Inject constructor(private val templateService: TemplateService) {

    suspend fun getTemplateModels(): Response<List<TemplateResponseDto>> =
        templateService.getTemplateModels()

}
