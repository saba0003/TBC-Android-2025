package com.example.tbc_android_2025.credit_card

import com.example.tbc_android_2025.commons.CreditCardType
import com.example.tbc_android_2025.commons.CreditCardUtils
import com.example.tbc_android_2025.commons.annotations.CreditCardNumber
import java.time.LocalDateTime

data class CreditCard(
    val type: CreditCardType,
    val name: String,
    @CreditCardNumber val number: String, // Custom Exceptions can be thrown at runtime
    val cvv: String,
    val expiry: LocalDateTime
) {
    init {
        CreditCardUtils.validateAnnotatedFields(target = this)
    }
}
