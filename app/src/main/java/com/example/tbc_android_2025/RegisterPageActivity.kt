package com.example.tbc_android_2025

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tbc_android_2025.databinding.ActivityRegisterPageBinding
import com.example.tbc_android_2025.utils.InputsValidator
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class RegisterPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterPageBinding
    private lateinit var registry: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState = savedInstanceState)
        binding = ActivityRegisterPageBinding.inflate(layoutInflater)
        registry = FirebaseAuth.getInstance()
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
                registry.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Snackbar.make(
                                binding.nextButton,
                                getString(R.string.successful_registration_label),
                                Snackbar.LENGTH_SHORT
                            ).show()
                            startActivity(Intent(this, LoginPageActivity::class.java))
                            finish()
                        } else {
                            val errorMessage = task.exception?.message
                                ?: getString(R.string.unsuccessful_registration_label)
                            Snackbar.make(
                                binding.nextButton,
                                errorMessage,
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
    }
}
