package com.example.tbc_android_2025

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tbc_android_2025.databinding.ActivityMainPageBinding

class MainPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState = savedInstanceState)
        binding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logInButton.setOnClickListener {
            startActivity(Intent(this, LoginPageActivity::class.java))
        }

        binding.registerButton.setOnClickListener {
            startActivity(Intent(this, RegisterPageActivity::class.java))
        }
    }
}
