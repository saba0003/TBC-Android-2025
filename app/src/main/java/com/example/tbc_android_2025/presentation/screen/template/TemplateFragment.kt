package com.example.tbc_android_2025.presentation.screen.template

import androidx.fragment.app.viewModels
import com.example.tbc_android_2025.databinding.FragmentTemplateBinding as Binding
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import com.example.tbc_android_2025.presentation.extensions.launchAndRepeatOnStart
import com.example.tbc_android_2025.presentation.screen.template.TemplateContract.State
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TemplateFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val viewModel: TemplateViewModel by viewModels()


    override fun bind() = collectObservers()


    /** ======================================= OBSERVERS ======================================= */
    private fun observeStates() = viewModel.state

    private fun collectObservers() {
        viewLifecycleOwner.launchAndRepeatOnStart {
            observeStates().collect { handleStates(group = it) }
        }
    }
    /** ========================================================================================= */


    /** ======================================= HANDLERS ======================================== */
    private fun handleStates(group: State) {}
    /** ========================================================================================= */
}
