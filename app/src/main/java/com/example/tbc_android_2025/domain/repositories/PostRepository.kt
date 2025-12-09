package com.example.tbc_android_2025.domain.repositories

import com.example.tbc_android_2025.domain.commons.Resource
import com.example.tbc_android_2025.domain.models.PostModel
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    fun getPostModels(): Flow<Resource<List<PostModel>>>

}
