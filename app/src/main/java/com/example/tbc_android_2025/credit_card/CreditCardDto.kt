package com.example.tbc_android_2025.credit_card

import java.time.LocalDate

data class CreditCardDto(
    val type: String,
    val name: String,
    val number: String,
    val cvv: String,
    val expiry: String
) {
    fun toDomain(): CreditCard {
        val (month, year) = expiry.split("/")
        val expiryDate = LocalDate.of("20$year".toInt(), month.toInt(), 1)

        return CreditCard(
            type = CreditCardType.valueOf(value = type), // you likely have this enum
            name = name,
            cvv = cvv,
            expiry = expiryDate,
            _number = number,
        )
    }
}
