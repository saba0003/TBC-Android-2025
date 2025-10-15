package com.example.tbc_android_2025

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.example.tbc_android_2025.databinding.ActivityUserAddBinding
import java.text.SimpleDateFormat
import java.util.*

class AddUserActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NEW_USER = "extra_new_user"
        const val EXTRA_PREVIOUS_QUERY = "extra_previous_query"
    }

    private lateinit var binding: ActivityUserAddBinding
    private val displayFormat = SimpleDateFormat("dd/MMM/yyyy", Locale.ENGLISH)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserAddBinding.inflate(layoutInflater)

        with(receiver = binding) {
            setContentView(root)
            birthdayEditText.setOnClickListener { showDatePicker() }
            addButton.setOnClickListener { saveUser() }
        }
    }

    private fun showDatePicker() {
        val c = Calendar.getInstance()
        val dp = DatePickerDialog(
            this,
            { _, y, m, d ->
                val cal = Calendar.getInstance().apply {
                    set(Calendar.YEAR, y)
                    set(Calendar.MONTH, m)
                    set(Calendar.DAY_OF_MONTH, d)
                }
                val display = displayFormat.format(cal.time)
                binding.birthdayEditText.setText(display)
            },
            c.get(Calendar.YEAR),
            c.get(Calendar.MONTH),
            c.get(Calendar.DAY_OF_MONTH)
        )
        dp.show()
    }

    private fun saveUser() {
        with(receiver = binding) {
            val first = firstNameEditText.text?.toString()?.trim() ?: ""
            val last = lastNameEditText.text?.toString()?.trim() ?: ""
            val birthdayDisplay = birthdayEditText.text?.toString()?.trim() ?: ""
            val address = addressEditText.text?.toString()?.trim() ?: ""
            val email = emailEditText.text?.toString()?.trim() ?: ""

            if (first.isEmpty() || last.isEmpty() || birthdayDisplay.isEmpty() || address.isEmpty() || email.isEmpty()) {
                firstNameEditText.error = if (first.isEmpty()) "Required" else null
                lastNameEditText.error = if (last.isEmpty()) "Required" else null
                birthdayEditText.error = if (birthdayDisplay.isEmpty()) "Required" else null
                addressEditText.error = if (address.isEmpty()) "Required" else null
                emailEditText.error = if (email.isEmpty()) "Required" else null
                return
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailEditText.error = "Invalid email"
                return
            }

            val birthdayMillis = try {
                val d = displayFormat.parse(birthdayDisplay)
                d?.time?.toString() ?: birthdayDisplay
            } catch (_: Exception) {
                birthdayDisplay
            }

            val id = (System.currentTimeMillis() % Int.MAX_VALUE).toInt()

            val newUser = User(
                id = id,
                firstName = first,
                lastName = last,
                birthday = birthdayMillis,
                address = address,
                email = email
            )

            val previousQuery = intent.getStringExtra(EXTRA_PREVIOUS_QUERY) ?: ""
            val out = Intent().apply {
                putExtra(EXTRA_NEW_USER, newUser)
                putExtra(EXTRA_PREVIOUS_QUERY, previousQuery)
            }
            setResult(RESULT_OK, out)
            finish()
        }
    }
}
