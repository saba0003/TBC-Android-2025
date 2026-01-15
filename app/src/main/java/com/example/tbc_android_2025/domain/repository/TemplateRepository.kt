package com.example.tbc_android_2025.domain.repository

import com.example.tbc_android_2025.domain.use_case.remote.TemplateModelsResourceFlow

interface TemplateRepository {

    fun getTemplateModelsFromRemote(): TemplateModelsResourceFlow

}
