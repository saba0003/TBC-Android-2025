package com.example.tbc_android_2025.presentation.screen.welcome

import android.Manifest.permission.POST_NOTIFICATIONS
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log.d
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tbc_android_2025.databinding.FragmentWelcomeBinding as Binding
import com.example.tbc_android_2025.presentation.common.fragment.BaseMviFragment
import com.example.tbc_android_2025.presentation.screen.welcome.WelcomeContract.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeFragment :
    BaseMviFragment<Binding, State, SideEffect, WelcomeViewModel>(inflater = Binding::inflate) {

    private val requestPermissionLauncher =
        registerForActivityResult(contract = ActivityResultContracts.RequestPermission()) {
            if (it)
                d(
                    LOGCAT_TAG_LOCAL_NOTIFICATION_DEBUG,
                    LOGCAT_MSG_LOCAL_NOTIFICATION_DEBUG_ACCESS_GRANTED
                )
            else
                d(
                    LOGCAT_TAG_LOCAL_NOTIFICATION_DEBUG,
                    LOGCAT_MSG_LOCAL_NOTIFICATION_DEBUG_ACCESS_DENIED
                )
        }


    override val viewModel: WelcomeViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view = view, savedInstanceState = savedInstanceState)
        checkNotificationPermission()
    }

    override fun listeners() {
        setListenerOnRegisterButton()
        setListenerOnLoginButton()
    }


    /** ======================================= LISTENERS ======================================= */
    private fun setListenerOnRegisterButton() = binding.registerButton.setOnClickListener {
        viewModel.onEvent(event = Event.OnRegisterClick)
    }

    private fun setListenerOnLoginButton() = binding.loginButton.setOnClickListener {
        viewModel.onEvent(event = Event.OnLoginClick)
    }
    /** ========================================================================================= */


    /** ======================================= HANDLERS ======================================== */
    override fun handleStates(state: State) = with(receiver = binding) {
        registerButton.isEnabled = state.isLoading.not()
        loginButton.isEnabled = state.isLoading.not()
    }

    override fun handleSideEffects(sideEffect: SideEffect) = when (sideEffect) {
        SideEffect.NavigateToRegister -> navigateToRegisterScreen()
        SideEffect.NavigateToLogin -> navigateToLoginScreen()
    }
    /** ========================================================================================= */


    /** ====================================== NAVIGATIONS ====================================== */
    private fun navigateToRegisterScreen() {
        val direction = WelcomeFragmentDirections.actionWelcomeFragmentToRegisterFragment()
        findNavController().navigate(directions = direction)
    }

    private fun navigateToLoginScreen() {
        val direction = WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment()
        findNavController().navigate(directions = direction)
    }
    /** ========================================================================================= */


    /** ========================================== AUX ========================================== */
    private fun checkNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            if (ContextCompat.checkSelfPermission(
                    requireContext(), POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            )
                requestPermissionLauncher.launch(input = POST_NOTIFICATIONS)
    }

    /** ========================================================================================= */


    private companion object {
        const val LOGCAT_TAG_LOCAL_NOTIFICATION_DEBUG = "NOTIFICATION_DEBUG"
        const val LOGCAT_MSG_LOCAL_NOTIFICATION_DEBUG_ACCESS_GRANTED = "Access Granted!"
        const val LOGCAT_MSG_LOCAL_NOTIFICATION_DEBUG_ACCESS_DENIED = "Access Denied!"
    }
}
