package com.example.tbc_android_2025

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tbc_android_2025.MainActivityHelper.setListenerOnAddButton
import com.example.tbc_android_2025.MainActivityHelper.setListenerOnRemoveButton
import com.example.tbc_android_2025.MainActivityHelper.setListenerOnUpdateButton
import com.example.tbc_android_2025.MainActivityHelper.updateCounters
import com.example.tbc_android_2025.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState = savedInstanceState)
        setUp()
    }

    private fun setUp() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        updateCounters(binding = binding)
        setContentView(view = binding.root)
        setListenerOnAddButton(binding = binding)
        setListenerOnUpdateButton(binding = binding)
        setListenerOnRemoveButton(binding = binding)
    }
}
