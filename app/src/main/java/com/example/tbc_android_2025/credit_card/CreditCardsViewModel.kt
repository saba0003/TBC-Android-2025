package com.example.tbc_android_2025.credit_card

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

typealias CreditCards = List<CreditCard>
typealias CreditCardsStateFlow = StateFlow<CreditCards>

class CreditCardsViewModel : ViewModel() {

    private val _creditCards = MutableStateFlow<CreditCards>(value = emptyList())
    val creditCards: CreditCardsStateFlow = _creditCards

    fun seed(cards: List<CreditCard>) {
        _creditCards.value = cards
    }
}
