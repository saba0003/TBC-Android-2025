package com.example.tbc_android_2025.presentation.mappers

import com.example.tbc_android_2025.domain.models.PostModel as PostModelDomain
import com.example.tbc_android_2025.presentation.screens.home.post.PostModel as PostModelPresentation

fun PostModelDomain.toPresentation() =
    PostModelPresentation(
        avatar = avatar,
        postDate = postDate,
        fullName = fullName,
        images = images,
        commentsCount = commentsCount,
        likesCount = likesCount,
        postDesc = postDesc,
        canComment = canComment,
        canPostPhoto = canPostPhoto
    )

fun List<PostModelDomain>.toPresentation() = map { it.toPresentation() }
