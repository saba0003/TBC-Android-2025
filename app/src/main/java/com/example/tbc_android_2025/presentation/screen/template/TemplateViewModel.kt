package com.example.tbc_android_2025.presentation.screen.template

import com.example.tbc_android_2025.presentation.common.BaseViewModel
import com.example.tbc_android_2025.presentation.screen.template.TemplateContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TemplateViewModel @Inject constructor() :
    BaseViewModel<State, Event, SideEffect>(initialState = State(isLoading = true))
