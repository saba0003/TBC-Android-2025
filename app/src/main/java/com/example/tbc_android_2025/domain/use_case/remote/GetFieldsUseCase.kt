package com.example.tbc_android_2025.domain.use_case.remote

import com.example.tbc_android_2025.domain.repository.FieldRepository
import com.example.tbc_android_2025.domain.repository.FieldsResourceFlow
import javax.inject.Inject

class GetFieldsUseCase @Inject constructor(private val fieldRepository: FieldRepository) {

    operator fun invoke(): FieldsResourceFlow = fieldRepository.getFields()

}
