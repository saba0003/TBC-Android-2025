package com.example.tbc_android_2025.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tbc_android_2025.presentation.navigation.AppNavigation
import com.example.tbc_android_2025.presentation.screen.splash.SplashViewModel
import com.example.tbc_android_2025.presentation.ui.theme.TBCAndroid2025Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: SplashViewModel by viewModels()
    private lateinit var navController: NavHostController


    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState = savedInstanceState)
        splashScreen.setKeepOnScreenCondition { viewModel.state.value.isLoading }
        setContent {
            navController = rememberNavController()
            TBCAndroid2025Theme { AppNavigation(navController = navController) }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent = intent)
        if (::navController.isInitialized)
            navController.handleDeepLink(intent = intent)
    }
}
