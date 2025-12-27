package com.example.tbc_android_2025.presentation.screen.template

import androidx.fragment.app.viewModels
import com.example.tbc_android_2025.presentation.common.Colors
import com.example.tbc_android_2025.databinding.FragmentTemplateBinding as Binding
import com.example.tbc_android_2025.presentation.common.BaseFragment
import com.example.tbc_android_2025.presentation.extension.asString
import com.example.tbc_android_2025.presentation.extension.popMessage
import com.example.tbc_android_2025.presentation.screen.template.TemplateContract.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TemplateFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val viewModel: TemplateViewModel by viewModels()


    override suspend fun collectObservers(): Unit = with(receiver = viewModel) {
        coroutineScope {
            launch { state.collect { handleStates(state = it) } }
            launch { sideEffect.collectLatest { handleSideEffects(sideEffect = it) } }
        }
    }


    /** ======================================= HANDLERS ======================================== */
    private fun handleStates(state: State) = Unit

    private fun handleSideEffects(sideEffect: SideEffect) = with(receiver = binding.root) {
        when (sideEffect) {
            is SideEffect.ShowError -> popMessage(
                text = sideEffect.error.asString(context = context),
                color = Colors.amaranth
            )
        }
    }
    /** ========================================================================================= */
}
