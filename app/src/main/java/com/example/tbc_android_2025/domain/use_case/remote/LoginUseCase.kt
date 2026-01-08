package com.example.tbc_android_2025.domain.use_case.remote

import com.example.tbc_android_2025.domain.model.request.LoginRequestModel
import com.example.tbc_android_2025.domain.repository.LoginRepository
import com.example.tbc_android_2025.domain.repository.LoginResourceFlow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    operator fun invoke(request: LoginRequestModel): LoginResourceFlow =
        loginRepository.login(request = request)

}
