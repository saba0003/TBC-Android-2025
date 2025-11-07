package com.example.tbc_android_2025.commons

object StringUtils {
    const val DEFAULT_CREDIT_CARD_NUMBER = "0000000000000000"
    const val ERR_CARD_LENGTH = "Credit card number must be exactly 16 digits!"
    const val ERR_CARD_NUMERIC_ONLY = "Credit card number must contain only numeric characters!"
    const val ERR_CARD_LUHN = "Invalid card number! (Luhn check failed)"
    const val ERR_CARD_TYPE = "Unsupported card type: %s"
}
