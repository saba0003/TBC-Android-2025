package com.example.tbc_android_2025.data.mappers

import com.example.tbc_android_2025.data.remote.dtos.PostResponseDto
import com.example.tbc_android_2025.domain.models.PostModel

fun PostResponseDto.toDomain() =
    PostModel(
        avatar = avatar,
        postDate = postDate,
        authorFullName = firstName.plus(other = ' ').plus(other = lastName),
        images = images,
        commentsCount = commentsCount,
        likesCount = likesCount,
        postDesc = postDesc,
        canComment = canComment,
        canPostPhoto = canPostPhoto
    )

fun List<PostResponseDto>.toDomain() = map { it.toDomain() }
