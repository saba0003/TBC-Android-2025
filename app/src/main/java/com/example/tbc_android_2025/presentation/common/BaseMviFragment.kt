package com.example.tbc_android_2025.presentation.common

import android.os.Bundle
import android.view.View
import com.example.tbc_android_2025.presentation.extension.launchAndRepeatOnStart
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import androidx.viewbinding.ViewBinding as Binding

abstract class BaseMviFragment<VB : Binding, STATE, SIDE_EFFECT, VM : BaseViewModel<STATE, *, SIDE_EFFECT>>(
    inflater: ViewBindingInflater<VB>
) : BaseFragment<VB>(inflater = inflater) {

    protected abstract val viewModel: VM


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectObservers()
    }


    /** ======================================= HANDLERS ======================================== */
    protected abstract fun handleStates(state: STATE)

    protected abstract fun handleSideEffects(sideEffect: SIDE_EFFECT)
    /** ========================================================================================= */


    /** AUX */
    private fun collectObservers() = viewLifecycleOwner.launchAndRepeatOnStart {
        launch { viewModel.state.collect { handleStates(state = it) } }
        launch { viewModel.sideEffect.collectLatest { handleSideEffects(sideEffect = it) } }
    }
}
