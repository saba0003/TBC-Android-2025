package com.example.tbc_android_2025.presentation.screens.home.post

data class PostModel(
    val avatar: String,
    val postDate: Long,
    val authorFullName: String,
    val images: List<String>,
    val commentsCount: Int,
    val likesCount: Int,
    val postDesc: String,
    val canComment: Boolean,
    val canPostPhoto: Boolean
)
