package com.example.tbc_android_2025.presentation.screen.template

import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.tbc_android_2025.common.Colors
import com.example.tbc_android_2025.common.Strings
import com.example.tbc_android_2025.domain.error.AppError.*
import com.example.tbc_android_2025.databinding.FragmentTemplateBinding as Binding
import com.example.tbc_android_2025.presentation.common.BaseFragment
import com.example.tbc_android_2025.presentation.extension.launchAndRepeatOnStart
import com.example.tbc_android_2025.presentation.extension.popMessage
import com.example.tbc_android_2025.presentation.screen.template.TemplateContract.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TemplateFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val viewModel: TemplateViewModel by viewModels()


    override fun bind() = collectObservers()


    /** ======================================= OBSERVERS ======================================= */
    private fun collectObservers() = viewLifecycleOwner.launchAndRepeatOnStart {
        launch { viewModel.state.collect { handleStates(state = it) } }
        launch { viewModel.sideEffect.collectLatest { handleSideEffects(sideEffect = it) } }
    }
    /** ========================================================================================= */


    /** ======================================= HANDLERS ======================================== */
    private fun handleStates(state: State) {}

    private fun handleSideEffects(sideEffect: SideEffect) = with(receiver = binding.root) {
        when (sideEffect) {
            is SideEffect.ShowError -> {
                val error = when (sideEffect.errorCode) {
                    NetworkError -> ContextCompat.getString(context, Strings.error_network)
                    ApiError -> ContextCompat.getString(context, Strings.error_api)
                    StateError -> ContextCompat.getString(context, Strings.error_state)
                    UnknownError -> ContextCompat.getString(context, Strings.error_unknown)
                    is Message -> sideEffect.errorCode.value
                }
                popMessage(text = error, color = Colors.amaranth)
            }
        }
    }
    /** ========================================================================================= */
}
