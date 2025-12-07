package com.example.tbc_android_2025.presentation.screen.cards

import androidx.lifecycle.viewModelScope
import com.example.tbc_android_2025.domain.commons.Resource
import com.example.tbc_android_2025.domain.use_cases.GetCardsUseCase
import com.example.tbc_android_2025.presentation.commons.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor(private val getCardsUseCase: GetCardsUseCase) :
    BaseViewModel<CardsState, CardsEvent, Unit>(initialState = CardsState(isLoading = true)) {


    init { onEvent(event = CardsEvent.GetCards) }


    override fun onEvent(event: CardsEvent): Unit = with(receiver = event) {
        when (this) {
            CardsEvent.GetCards -> handleGetCards()
        }
    }


    /** ======================================= HANDLERS ======================================== */
    private fun handleGetCards() = viewModelScope.launch {
        getCardsUseCase().collect {
            when (it) {
                is Resource.Success -> updateState { CardsState(data = it.data) }
                is Resource.Error -> updateState { CardsState(error = it.errorMessage) }
                is Resource.Loader -> updateState { CardsState(isLoading = it.isLoading) }
            }
        }
    }
    /** ========================================================================================= */
}
