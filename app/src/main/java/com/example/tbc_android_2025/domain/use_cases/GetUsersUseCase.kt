package com.example.tbc_android_2025.domain.use_cases

import com.example.tbc_android_2025.domain.commons.Resource
import com.example.tbc_android_2025.domain.models.responses.UsersPage
import com.example.tbc_android_2025.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val userRepository: UserRepository) {

    operator fun invoke(page: Int): Flow<Resource<UsersPage>> = userRepository.getUsers(page = page)

}
