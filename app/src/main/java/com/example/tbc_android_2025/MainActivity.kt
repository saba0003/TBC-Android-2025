package com.example.tbc_android_2025

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tbc_android_2025.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState = savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        populateUI()
    }

    private fun populateUI() {
        val landscapeInfo = """
            This vast mountain range is renowned for its remarkable diversity in terms of
            topography and climate. It features towering peaks, active volcanoes, deep canyons,
            expansive plateaus, and lush valleys. The Andes are also home to
        """.trimIndent().replace(oldValue = "\n", newValue = " ")
        binding.priceTag.text = String.format(getString(R.string.price_tag), 230)
        binding.time.text = String.format(getString(R.string.time_label), 8)
        binding.temperature.text = String.format(getString(R.string.temperature_celsius_label), 16)
        binding.rating.text = String.format(getString(R.string.rating_label), 4.5f)
        binding.landscapeInfo.text = String.format(getString(R.string.landscape_info_label), landscapeInfo)
    }
}
