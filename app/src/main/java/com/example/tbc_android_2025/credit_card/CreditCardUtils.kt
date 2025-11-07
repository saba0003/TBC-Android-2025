package com.example.tbc_android_2025.credit_card

object CreditCardUtils {

    private val CARD_NUMBER_REGEX = Regex(pattern = "^\\d{16}$")

    fun isValid(number: String): Boolean {
        return try {
            validateNumber(number = number)
            true
        } catch (_: CreditCardValidationException) {
            false
        }
    }

    fun validateNumber(number: String) {
        if (!number.all { it.isDigit() })
            throw InvalidCardCharactersException()

        if (!CARD_NUMBER_REGEX.matches(input = number))
            throw InvalidCardLengthException()

        if (!passesLuhn(number))
            throw InvalidCardChecksumException()
    }

    /**
     * Mask for display (e.g., **** **** **** 1234)
     *
     * @throws CreditCardValidationException if number is invalid
     */
    fun maskNumberRaw(number: String): String {
        validateNumber(number = number)
        return number.takeLast(n = 4)
    }

    /** Luhn Algorithm */
    private fun passesLuhn(number: String): Boolean {
        val digits = number.map { it.digitToInt() }.reversed()

        val sum = digits.mapIndexed { index, digit ->
            if (index % 2 == 1) {
                val doubled = digit * 2
                if (doubled > 9) doubled - 9 else doubled
            } else {
                digit
            }
        }.sum()

        return sum % 10 == 0
    }
}
