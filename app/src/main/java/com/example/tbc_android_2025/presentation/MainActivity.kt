package com.example.tbc_android_2025.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tbc_android_2025.data.HttpClient
import com.example.tbc_android_2025.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState = savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(view = binding.root)
        HttpClient.init(context = this)
    }
}