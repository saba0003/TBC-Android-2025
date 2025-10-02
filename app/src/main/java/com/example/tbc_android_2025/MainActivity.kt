package com.example.tbc_android_2025

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tbc_android_2025.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var users: MutableList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        users = mutableListOf()
        updateUserCount()

        setContentView(binding.root)

        binding.addButton.setOnClickListener {
            val fullName: String = binding.fullNameInput.text.toString().trim()
            val email: String = binding.emailAddInput.text.toString().trim()
            if (validateInputs(fullName = fullName, email = email)) {
                users.add(element = User(fullName = fullName, email = email))
                updateUserCount()
                binding.fullNameInput.text.clear()
                binding.emailAddInput.text.clear()
            }
        }

        binding.searchButton.setOnClickListener {
            val email: String = binding.emailSearchInput.text.toString().trim()
            searchByEmail(email = email)
        }

        binding.goBackButton.setOnClickListener {
            displayInputPage()
        }
    }

    private fun displayInputPage() {
        binding.emailSearchInput.text.clear()
        binding.infoLayout.visibility = View.GONE
        binding.inputLayout.visibility = View.VISIBLE
    }

    private fun displayInfoPage() {
        binding.inputLayout.visibility = View.GONE
        binding.infoLayout.visibility = View.VISIBLE
    }

    private fun updateUserCount() {
        binding.userCounter.text = getString(R.string.user_count, users.size)
    }

    private fun validateInputs(fullName: String, email: String): Boolean {
        return validateFullName(fullName = fullName).and(validateEmail(email = email))
    }

    private fun validateFullName(fullName: String): Boolean {
        return if (fullName.isBlank()) {
            showTempMessage(message = getString(R.string.all_fields_must_be_filled))
            false
        } else true
    }

    private fun validateEmail(email: String): Boolean {
        if (email.isBlank()) {
            showTempMessage(message = getString(R.string.all_fields_must_be_filled))
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showTempMessage(message = getString(R.string.invalid_email_address))
            return false
        }

        return true
    }

    private fun searchByEmail(email: String) {
        if (!validateEmail(email = email))
            return

        val user = users.find { it.email == email }
        if (user != null) {
            binding.fullNameDisplay.text = getString(R.string.full_name, user.fullName)
            binding.emailDisplay.text = getString(R.string.email, user.email)
            displayInfoPage()
        } else {
            showTempMessage(message = getString(R.string.user_not_found))
        }
    }

    private fun showTempMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
