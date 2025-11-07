package com.example.tbc_android_2025.credit_card

import com.example.tbc_android_2025.commons.StringUtils.ERR_CARD_LENGTH
import com.example.tbc_android_2025.commons.StringUtils.ERR_CARD_NUMERIC_ONLY
import com.example.tbc_android_2025.commons.StringUtils.ERR_CARD_LUHN
import com.example.tbc_android_2025.commons.StringUtils.ERR_CARD_TYPE

sealed class CreditCardValidationException(message: String) : IllegalArgumentException(message)

class InvalidCardLengthException(message: String = ERR_CARD_LENGTH) :
    CreditCardValidationException(message = message)

class InvalidCardCharactersException(message: String = ERR_CARD_NUMERIC_ONLY) :
    CreditCardValidationException(message = message)

class InvalidCardChecksumException(message: String = ERR_CARD_LUHN) :
    CreditCardValidationException(message = message)

class UnsupportedCardTypeException(type: CreditCardType) :
    CreditCardValidationException(message = ERR_CARD_TYPE.format(type))
