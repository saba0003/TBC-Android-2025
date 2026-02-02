package com.example.tbc_android_2025.domain.repository

import com.example.tbc_android_2025.domain.common.Resource
import com.example.tbc_android_2025.domain.model.FieldModel
import kotlinx.coroutines.flow.Flow

typealias FieldsResourceFlow = Flow<Resource<List<List<FieldModel>>>>

interface FieldRepository {
    fun getFields(): FieldsResourceFlow
}
