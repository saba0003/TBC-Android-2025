package com.example.register.domain.repository

import com.example.domain.common.Resource
import com.example.register.domain.model.FieldModel
import kotlinx.coroutines.flow.Flow

typealias FieldsResourceFlow = Flow<Resource<List<List<FieldModel>>>>

interface FieldRepository {
    fun getFields(): FieldsResourceFlow
}
