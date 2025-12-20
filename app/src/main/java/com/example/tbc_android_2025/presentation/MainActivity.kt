package com.example.tbc_android_2025.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.tbc_android_2025.presentation.screen.splash.SplashState
import com.example.tbc_android_2025.presentation.screen.splash.SplashViewModel
import com.example.tbc_android_2025.databinding.ActivityMainBinding as Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: Binding? = null
    private val binding get() = _binding!!
    private val viewModel: SplashViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState = savedInstanceState)
        enableEdgeToEdge() // for jetpack compose, this line should be placed before super.onCreate and setContent
        _binding = Binding.inflate(layoutInflater)
        setContentView(view = binding.root)
        splashScreen.setKeepOnScreenCondition { viewModel.state.value is SplashState.Loading }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
