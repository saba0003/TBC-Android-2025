package com.example.tbc_android_2025.domain.models

data class PostModel(
    val avatar: String,
    val postDate: Long,
    val fullName: String,
    val images: List<String>,
    val commentsCount: Int,
    val likesCount: Int,
    val postDesc: String,
    val canComment: Boolean,
    val canPostPhoto: Boolean
)
