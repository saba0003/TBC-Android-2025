package com.example.tbc_android_2025.domain.use_case.remote

import com.example.tbc_android_2025.domain.model.request.RegisterRequestModel
import com.example.tbc_android_2025.domain.repository.RegisterRepository
import com.example.tbc_android_2025.domain.repository.RegisterResourceFlow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val registerRepository: RegisterRepository) {

    operator fun invoke(request: RegisterRequestModel): RegisterResourceFlow =
        registerRepository.register(request = request)

}
