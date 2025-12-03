package com.example.tbc_android_2025.presentation.screen.security

import androidx.fragment.app.viewModels
import com.example.tbc_android_2025.commons.Drawables
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import com.example.tbc_android_2025.presentation.extensions.launchAndRepeatOnStart
import com.example.tbc_android_2025.presentation.extensions.popMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.example.tbc_android_2025.databinding.FragmentSecurityScreenBinding as Binding

@AndroidEntryPoint
class SecurityFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val viewModel: SecurityViewModel by viewModels()


    override fun bind() {
        collectObservers()
        updateDots()
    }

    override fun listeners() {
        setListenersOnNumpadButtons()
        setListenerOnBackspaceButton()
    }


    /** ================================== OBSERVERS ============================================ */
    private fun collectObservers() = viewLifecycleOwner.launchAndRepeatOnStart {
        launch { observeStates().collect { updateDots(input = it.input) } }

        launch {
            observeSideEffects().collect {
                when (it) {
                    is SecurityContract.SideEffect.ShowMessage ->
                        binding.root.popMessage(text = it.text, color = it.color)
                }
            }
        }
    }

    private fun observeStates() = viewModel.state

    private fun observeSideEffects() = viewModel.sideEffect
    /** ========================================================================================= */


    /** ================================== LISTENERS ============================================ */
    private fun setListenersOnNumpadButtons() = with(receiver = binding) {
        val numpadButtons = listOf(btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)
        numpadButtons.forEach { btn ->
            btn.setOnClickListener {
                viewModel.onEvent(event = SecurityContract.Event.DigitPressed(digit = btn.text.toString()))
            }
        }
    }

    private fun setListenerOnBackspaceButton() = binding.backspaceButton.setOnClickListener {
        viewModel.onEvent(event = SecurityContract.Event.BackspacePressed)
    }
    /** ========================================================================================= */


    /** ===================================== AUX =============================================== */
    private fun updateDots(input: String = "") = with(receiver = binding) {
        val dots = listOf(dot1, dot2, dot3, dot4)
        dots.forEachIndexed { i, dot ->
            dot.setBackgroundResource(
                if (i < input.length)
                    Drawables.shape_dot_filled
                else
                    Drawables.shape_dot_empty
            )
        }
    }
    /** ========================================================================================= */
}
