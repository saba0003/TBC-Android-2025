package com.example.register.domain.use_case.remote

import com.example.register.domain.repository.FieldRepository
import com.example.register.domain.repository.FieldsResourceFlow
import javax.inject.Inject

class GetFieldsUseCase @Inject constructor(private val fieldRepository: FieldRepository) {

    operator fun invoke(): FieldsResourceFlow = fieldRepository.getFields()

}
