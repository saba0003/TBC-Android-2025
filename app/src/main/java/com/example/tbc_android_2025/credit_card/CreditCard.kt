package com.example.tbc_android_2025.credit_card

import com.example.tbc_android_2025.commons.CreditCardType
import com.example.tbc_android_2025.commons.StringUtils.DEFAULT_CREDIT_CARD_NUMBER
import com.example.tbc_android_2025.commons.annotations.CreditCardNumber
import java.time.LocalDate

data class CreditCard(
    val type: CreditCardType,
    val name: String,
    val cvv: String,
    val expiry: LocalDate
) {
    @CreditCardNumber
    var number: String by CreditCardNumberDelegate(initialValue = DEFAULT_CREDIT_CARD_NUMBER)
}
