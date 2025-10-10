package com.example.tbc_android_2025

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tbc_android_2025.databinding.ActivityRegisterPageBinding
import com.example.tbc_android_2025.utils.InputsValidator

class RegisterPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState = savedInstanceState)
        binding = ActivityRegisterPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }

        binding.nextButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            if (InputsValidator.validateInputs(
                    emailView = binding.emailEditText,
                    email = email,
                    passwordView = binding.passwordEditText,
                    password = password
                )
            ) {
                startActivity(Intent(this, LoginPageActivity::class.java))
                finish()
            }
        }
    }
}
