package com.example.tbc_android_2025

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tbc_android_2025.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var cachedEmail = ""
    private var cachedUsername = ""
    private var cachedFirstName = ""
    private var cachedLastName = ""
    private var cachedAge = ""
    private var cachedPhoneNumber = ""
    private var cachedAddress = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(view = binding.root)

        binding.clearButton.setOnLongClickListener {
            clearFields()
            showTempMessage(message = "Fields cleared")
            true
        }

        binding.saveButton.setOnClickListener {
            saveProfile()
        }

        binding.againButton.setOnClickListener {
            showInputLayout()
        }
    }

    private fun saveProfile() {
        val email = binding.emailInput.text.toString().trim()
        val username = binding.usernameInput.text.toString().trim()
        val firstName = binding.firstNameInput.text.toString().trim()
        val lastName = binding.lastNameInput.text.toString().trim()
        val ageStr = binding.ageInput.text.toString().trim()
        val phoneNumberStr = binding.phoneInput.text.toString().trim()
        val address = binding.addressInput.text.toString().trim()

        val error =
            validateInputs(email, username, firstName, lastName, ageStr, phoneNumberStr, address)

        if (error != null) {
            showTempMessage(message = error)
            return
        }

        cacheProfile(
            email = email,
            username = username,
            firstName = firstName,
            lastName = lastName,
            age = ageStr,
            phone = phoneNumberStr,
            address = address
        )
        displayProfileInfo(
            email = email,
            username = username,
            firstName = firstName,
            lastName = lastName,
            age = ageStr
        )
        showTempMessage(message = "Profile saved successfully!")
    }

    private fun validateInputs(
        email: String,
        username: String,
        firstName: String,
        lastName: String,
        ageStr: String,
        phoneNumberStr: String,
        address: String
    ): String? {
        return when {
            email.isBlank() || username.isBlank() ||
                    firstName.isBlank() || lastName.isBlank() ||
                    ageStr.isBlank() || phoneNumberStr.isBlank() || address.isBlank() ->
                "All fields must be filled!"
            username.length < 10 -> "Username must be at least 10 characters long."
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Invalid email address."
            !ageStr.all { it.isDigit() } -> "Age must be a number."
            ageStr.toInt() <= 0 || ageStr.toInt() > 200 -> "Age must be between 1 and 200."
            !Patterns.PHONE.matcher(phoneNumberStr).matches() -> "Invalid phone number."
            else -> null
        }
    }

    private fun cacheProfile(
        email: String,
        username: String,
        firstName: String,
        lastName: String,
        age: String,
        phone: String,
        address: String
    ) {
        cachedEmail = email
        cachedUsername = username
        cachedFirstName = firstName
        cachedLastName = lastName
        cachedAge = age
        cachedPhoneNumber = phone
        cachedAddress = address
    }

    @SuppressLint("SetTextI18n")
    private fun displayProfileInfo(
        email: String,
        username: String,
        firstName: String,
        lastName: String,
        age: String
    ) {
        binding.savedEmail.text = "Email: $email"
        binding.savedUsername.text = "Username: $username"
        binding.savedFullName.text = "Full Name: $firstName $lastName"
        binding.savedAge.text = "Age: $age"

        binding.inputLayout.visibility = View.GONE
        binding.savedLayout.visibility = View.VISIBLE
    }

    private fun clearFields() {
        binding.emailInput.text?.clear()
        binding.usernameInput.text?.clear()
        binding.firstNameInput.text?.clear()
        binding.lastNameInput.text?.clear()
        binding.ageInput.text?.clear()
        binding.phoneInput.text?.clear()
        binding.addressInput.text?.clear()
    }

    private fun autofillFields() {
        binding.emailInput.setText(cachedEmail)
        binding.usernameInput.setText(cachedUsername)
        binding.firstNameInput.setText(cachedFirstName)
        binding.lastNameInput.setText(cachedLastName)
        binding.ageInput.setText(cachedAge)
        binding.phoneInput.setText(cachedPhoneNumber)
        binding.addressInput.setText(cachedAddress)
    }

    private fun showInputLayout() {
        binding.inputLayout.visibility = View.VISIBLE
        binding.savedLayout.visibility = View.GONE
        autofillFields()
    }

    private fun showTempMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
