package com.example.tbc_android_2025.utils

import android.util.Patterns
import androidx.appcompat.widget.AppCompatEditText
import com.example.tbc_android_2025.R
import com.example.tbc_android_2025.extensions.popMessage

object EmailValidator {

    fun validateEmail(emailEditText: AppCompatEditText, emailInput: String): Boolean {
        return when {
            emailInput.isBlank() -> {
                emailEditText.popMessage(
                    resId = R.string.empty_email_input_label,
                    color = R.color.amaranth
                )
                false
            }

            !Patterns.EMAIL_ADDRESS.matcher(emailInput).matches() -> {
                emailEditText.popMessage(
                    resId = R.string.incorrect_email_input_format_label,
                    color = R.color.amaranth
                )
                false
            }

            else -> true
        }
    }
}
