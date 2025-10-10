package com.example.tbc_android_2025

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tbc_android_2025.databinding.ActivityLoginPageBinding
import com.example.tbc_android_2025.utils.InputsValidator

class LoginPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState = savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }

        binding.logInButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            if (InputsValidator.validateInputs(
                    emailView = binding.emailEditText,
                    passwordView = binding.passwordEditText,
                    email = email,
                    password = password
                )
            ) {
                finish()
            }
        }
    }
}
