package com.example.tbc_android_2025.utils

import android.util.Patterns
import com.example.tbc_android_2025.R
import com.example.tbc_android_2025.databinding.FragmentMainBinding
import com.example.tbc_android_2025.databinding.FragmentUpdateBinding

object EmailValidator {

    fun validateEmail(binding: FragmentMainBinding, email: String): Boolean = run {
        validate(email = email) { resId ->
            binding.emailEditText.popMessage(resId = resId, color = R.color.amaranth)
        }
    }

    fun validateEmail(binding: FragmentUpdateBinding, email: String): Boolean = run {
        validate(email = email) { resId ->
            binding.emailEditText.popMessage(resId = resId, color = R.color.amaranth)
        }
    }

    private fun validate(email: String, showError: (Int) -> Unit): Boolean = run {
        when {
            email.isBlank() -> {
                showError(R.string.empty_email_input_label)
                false
            }

            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                showError(R.string.incorrect_email_input_format_label)
                false
            }

            else -> true
        }
    }
}
