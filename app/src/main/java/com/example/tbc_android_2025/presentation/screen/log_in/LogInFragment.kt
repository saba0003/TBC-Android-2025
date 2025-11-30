package com.example.tbc_android_2025.presentation.screen.log_in

import androidx.fragment.app.viewModels
import com.example.tbc_android_2025.databinding.FragmentLoginBinding as Binding
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogInFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val logInViewModel: LogInViewModel by viewModels()


    override fun bind() {
        TODO(reason = "Not yet implemented")
    }

    override fun listeners() {
        TODO(reason = "Not yet implemented")
    }

}
