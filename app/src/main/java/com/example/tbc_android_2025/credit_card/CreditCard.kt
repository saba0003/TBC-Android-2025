package com.example.tbc_android_2025.credit_card

import com.example.tbc_android_2025.commons.StringUtils.DEFAULT_CREDIT_CARD_NUMBER
import java.time.LocalDate

data class CreditCard(
    val type: CreditCardType,
    val name: String,
    val cvv: String,
    val expiry: LocalDate,
    private var _number: String
) {
    @CreditCardNumber
    var number: String by CreditCardNumberDelegate(initialValue = DEFAULT_CREDIT_CARD_NUMBER)

    init {
        number = _number
    }
}
