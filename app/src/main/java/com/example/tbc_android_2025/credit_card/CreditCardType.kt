package com.example.tbc_android_2025.credit_card

enum class CreditCardType(mii: Int) {

    VISA(mii = 4),
    MASTERCARD(mii = 5);

    fun fromString(raw: String): CreditCardType =
        entries.firstOrNull { it.name.equals(other = raw, ignoreCase = true) }
            ?: throw UnsupportedCardTypeException(type = this)
}
