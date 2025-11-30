package com.example.tbc_android_2025.presentation.screen.register

import androidx.fragment.app.viewModels
import com.example.tbc_android_2025.databinding.FragmentRegisterBinding as Binding
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val registerViewModel: RegisterViewModel by viewModels()


    override fun bind() {
        TODO(reason = "Not yet implemented")
    }

    override fun listeners() {
        TODO("Not yet implemented")
    }

}
