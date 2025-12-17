package com.example.tbc_android_2025.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tbc_android_2025.databinding.ActivityMainBinding as Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: Binding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState = savedInstanceState)
        _binding = Binding.inflate(layoutInflater)
        setContentView(view = binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
