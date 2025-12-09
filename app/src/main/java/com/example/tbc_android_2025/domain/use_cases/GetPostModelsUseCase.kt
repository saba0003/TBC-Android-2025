package com.example.tbc_android_2025.domain.use_cases

import com.example.tbc_android_2025.di.qualifiers.RemoteRepository
import com.example.tbc_android_2025.domain.commons.Resource
import com.example.tbc_android_2025.domain.models.PostModel
import com.example.tbc_android_2025.domain.repositories.PostRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostModelsUseCase @Inject constructor(@param:RemoteRepository private val postRepository: PostRepository) {

    operator fun invoke(): Flow<Resource<List<PostModel>>> = postRepository.getPostModels()

}
