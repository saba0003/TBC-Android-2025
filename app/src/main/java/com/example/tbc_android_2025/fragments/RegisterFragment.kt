package com.example.tbc_android_2025.fragments

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tbc_android_2025.commons.BaseFragment
import com.example.tbc_android_2025.commons.Colors
import com.example.tbc_android_2025.commons.Ids
import com.example.tbc_android_2025.extensions.popMessage
import com.example.tbc_android_2025.user.User
import com.example.tbc_android_2025.user.UserViewModel
import kotlin.getValue
import com.example.tbc_android_2025.databinding.FragmentRegisterBinding as Binding

class RegisterFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val userViewModel: UserViewModel by viewModels()


    override fun listeners() = setListenerOnRegisterButton()


    /** ===================================== AUX =============================================== */
    private fun setListenerOnRegisterButton() = with(receiver = binding) {
        registerButton.setOnClickListener { view ->

            val email = emailEditText.text.toString().trim()
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                view.popMessage(
                    text = "All fields must be filled!",
                    color = Colors.amaranth
                )
                return@setOnClickListener
            }

            userViewModel.registerUserRemote(email = email, password = password) { result ->
                result.onSuccess { res ->

                    userViewModel.addUser(
                        User(
                            email = email,
                            username = username,
                            password = password
                        )
                    )

                    view.popMessage(
                        text = "Registered successfully! Token: ${res.token}",
                        color = Colors.light_green
                    )

                    findNavController().navigate(resId = Ids.action_registerFragment_to_welcomeFragment)
                }
                result.onFailure { e ->
                    view.popMessage(
                        text = "Registration failed: ${e.message}",
                        color = Colors.amaranth
                    )
                }
            }
        }
    }
    /** ========================================================================================= */
}
