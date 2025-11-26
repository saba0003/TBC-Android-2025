package com.example.tbc_android_2025.domain.use_cases

import com.example.tbc_android_2025.data.commons.Resource
import com.example.tbc_android_2025.data.commons.ResponseHandler
import com.example.tbc_android_2025.data.extensions.toDomainList
import com.example.tbc_android_2025.data.network.ChatRepository
import com.example.tbc_android_2025.domain.models.Chat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetChatsUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
    private val responseHandler: ResponseHandler
) {
    operator fun invoke(): Flow<Resource<List<Chat>>> =
        responseHandler.safeApiCall { chatRepository.getChats() }
            .map {
                with(receiver = it) {
                    when (this) {
                        is Resource.Success ->
                            Resource.Success(data = data.toDomainList())

                        is Resource.Error ->
                            Resource.Error(errorMessage = errorMessage, throwable = throwable)

                        is Resource.Loader ->
                            Resource.Loader(isLoading = isLoading)
                    }
                }
            }
}
