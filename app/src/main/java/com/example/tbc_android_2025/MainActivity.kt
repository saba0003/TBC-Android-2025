package com.example.tbc_android_2025

import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import com.example.tbc_android_2025.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        d("method", "onStart")
    }

    override fun onResume() {
        super.onResume()
        d("method", "onResume")
    }

    override fun onPause() {
        super.onPause()
        d("method", "onPause")
    }

    override fun onStop() {
        super.onStop()
        d("method", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        d("method", "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        d("method", "onRestart")
    }
}
