package com.example.tbc_android_2025.presentation.screen.store_gallery

interface StoreGalleryContract {
    data class State(val isLoading: Boolean = NOT_YET_STARTED) {
        companion object {
            private const val NOT_YET_STARTED = false
            private const val LOADING = true

            fun loading() = State(isLoading = LOADING)
        }
    }

    sealed interface Event {
    }

    sealed interface SideEffect {
    }
}
