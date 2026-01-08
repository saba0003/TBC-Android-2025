package com.example.tbc_android_2025.domain.use_case.remote

import com.example.tbc_android_2025.domain.repository.UserRepository
import com.example.tbc_android_2025.domain.repository.UsersPageFlow
import javax.inject.Inject

class GetUsersPageFromRemoteUseCase @Inject constructor(private val userRepository: UserRepository) {

    operator fun invoke(page: Int = STARTING_PAGE): UsersPageFlow =
        userRepository.getUsersPageFromRemote(page = page)

    private companion object {
        const val STARTING_PAGE = 1
    }
}
