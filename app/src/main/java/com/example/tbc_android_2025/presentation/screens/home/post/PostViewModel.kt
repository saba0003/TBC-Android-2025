package com.example.tbc_android_2025.presentation.screens.home.post

import androidx.lifecycle.viewModelScope
import com.example.tbc_android_2025.domain.commons.Resource.*
import com.example.tbc_android_2025.domain.use_cases.GetPostModelsUseCase
import com.example.tbc_android_2025.presentation.commons.BaseViewModel
import com.example.tbc_android_2025.presentation.mappers.toPresentation
import com.example.tbc_android_2025.presentation.screens.home.post.PostContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val getTemplateModels: GetPostModelsUseCase) :
    BaseViewModel<State, Event, Unit>(initialState = State(isLoading = true)) {


    init {
        onEvent(event = Event.GetPostModels)
    }


    override fun onEvent(event: Event): Unit = with(receiver = event) {
        when (this) {
            Event.GetPostModels -> onGetTemplateModels()
        }
    }


    /** ======================================= HANDLERS ======================================== */
    private fun onGetTemplateModels() = viewModelScope.launch {
        getTemplateModels().collect {
            when (it) {
                is Success -> updateState { State(posts = it.data.toPresentation()) }
                is Error -> updateState { State(error = it.errorMessage) }
                is Loader -> updateState { State(isLoading = it.isLoading) }
            }
        }
    }
    /** ========================================================================================= */
}
