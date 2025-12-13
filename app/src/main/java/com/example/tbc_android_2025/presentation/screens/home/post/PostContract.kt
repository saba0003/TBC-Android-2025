package com.example.tbc_android_2025.presentation.screens.home.post

import com.example.tbc_android_2025.domain.exceptions.AppError

sealed interface PostContract {

    data class State(
        val posts: List<PostModel> = emptyList(),
        val error: AppError? = null,
        val isLoading: Boolean = false
    )

    sealed interface Event {
        data object GetPostModels : Event
    }

}
