package com.example.tbc_android_2025.credit_card

import kotlin.reflect.KProperty

class CreditCardNumberDelegate(initialValue: String) {

    private var field: String = initialValue
        set(value) {
            CreditCardUtils.validateNumber(number = value) // runtime validation
            field = value
        }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String = field

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        field = value
    }
}
