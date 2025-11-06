package com.example.tbc_android_2025.commons

import com.example.tbc_android_2025.commons.annotations.CreditCardNumber
import com.example.tbc_android_2025.commons.exceptions.*

object CreditCardUtils {

    private val CARD_NUMBER_REGEX = Regex(pattern = "^\\d{16}$")

    /**
     * Validates credit card number and throws specific exceptions when invalid.
     *
     * Rules:
     * - Must be exactly 16 characters long
     * - Only digits allowed
     * - Must pass Luhn checksum
     *
     * @throws InvalidCardLengthException if not 16 digits
     * @throws InvalidCardCharactersException if contains non-digit characters
     * @throws InvalidCardChecksumException if it fails Luhn algorithm
     */

    /**
     * Returns true if the number is structurally & checksum valid,
     * without throwing exceptions.
     */
    fun isValid(number: String): Boolean {
        return try {
            validateNumber(number = number)
            true
        } catch (_: CreditCardValidationException) {
            false
        }
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

    fun validateNumber(number: String) {
        if (!number.all { it.isDigit() })
            throw InvalidCardCharactersException()

        if (!CARD_NUMBER_REGEX.matches(input = number))
            throw InvalidCardLengthException()

        if (!passesLuhn(number))
            throw InvalidCardChecksumException()
    }

    fun validateAnnotatedFields(target: Any) {
        val clazz = target::class
        val props = clazz.members

        props.forEach { prop ->
            if (prop.annotations.any { it is CreditCardNumber }) {
                (prop.call(target) as? String ?: return@forEach).apply {
                    validateNumber(number = this)
                }
            }
        }
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
