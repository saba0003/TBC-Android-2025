package com.example.tbc_android_2025.utils

import android.util.Patterns
import android.view.View
import com.example.tbc_android_2025.R
import com.google.android.material.snackbar.Snackbar

object InputsValidator {

    fun validateInputs(emailView: View, email: String, passwordView: View, password: String): Boolean {

        fun validateEmail(view: View, email: String): Boolean {
            return when {
                email.isBlank() -> {
                    Snackbar.make(
                        view,
                        R.string.empty_email_input_label,
                        Snackbar.LENGTH_SHORT
                    ).show()
                    false
                }

                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    Snackbar.make(
                        view,
                        R.string.incorrect_email_input_format_label,
                        Snackbar.LENGTH_SHORT
                    ).show()
                    false
                }

                else -> true
            }
        }

        fun validatePassword(view: View, password: String): Boolean {
            return if (password.length < 8) {
                Snackbar.make(
                    view,
                    R.string.insufficient_password_input_length_label,
                    Snackbar.LENGTH_SHORT
                ).show()
                false
            } else {
                true
            }
        }

        return validateEmail(view = emailView, email = email) &&
                validatePassword(view = passwordView, password = password)
    }
}