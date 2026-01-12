package com.example.tbc_android_2025.presentation.screen.template

import androidx.fragment.app.viewModels
import com.example.tbc_android_2025.presentation.common.Colors
import com.example.tbc_android_2025.databinding.FragmentTemplateBinding as Binding
import com.example.tbc_android_2025.presentation.common.fragment.BaseMviFragment
import com.example.tbc_android_2025.presentation.extension.asString
import com.example.tbc_android_2025.presentation.extension.popMessage
import com.example.tbc_android_2025.presentation.screen.template.TemplateContract.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TemplateFragment :
    BaseMviFragment<Binding, State, SideEffect, TemplateViewModel>(inflater = Binding::inflate) {

    override val viewModel: TemplateViewModel by viewModels()


    /** ======================================= HANDLERS ======================================== */
    override fun handleStates(state: State) = Unit

    override fun handleSideEffects(sideEffect: SideEffect) = with(receiver = binding.root) {
        when (sideEffect) {
            is SideEffect.ShowError -> popMessage(
                text = sideEffect.error.asString(context = context),
                color = Colors.amaranth
            )
        }
    }
    /** ========================================================================================= */
}
