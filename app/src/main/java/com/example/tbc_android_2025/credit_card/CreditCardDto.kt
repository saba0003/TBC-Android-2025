package com.example.tbc_android_2025.credit_card

import com.example.tbc_android_2025.commons.StringUtils.DESERIALIZATION_DELIMITER
import com.example.tbc_android_2025.commons.StringUtils.DESERIALIZATION_PREFIX
import java.time.LocalDate

data class CreditCardDto(
    val type: String,
    val name: String,
    val number: String,
    val cvv: String,
    val expiry: String
) {
    fun toDomain(): CreditCard {
        val (month, year) = expiry.split(DESERIALIZATION_DELIMITER)
        val expiryDate = LocalDate.of(DESERIALIZATION_PREFIX.format(year).toInt(), month.toInt(), 1)

        return CreditCard(
            type = CreditCardType.valueOf(value = type),
            name = name,
            cvv = cvv,
            expiry = expiryDate,
            _number = number,
        )
    }
}
