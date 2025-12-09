package com.example.tbc_android_2025.presentation.screens.home.post

sealed interface PostContract {

    data class State(
        val posts: List<PostModel> = emptyList(),
        val error: String? = null,
        val isLoading: Boolean = false
    )

    sealed interface Event {
        data object GetPostModels : Event
    }

}
