package com.example.tbc_android_2025.presentation.screen.cards

import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
import com.example.tbc_android_2025.databinding.FragmentCardsBinding as Binding
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import com.example.tbc_android_2025.presentation.extensions.launchAndRepeatOnStart
import com.example.tbc_android_2025.presentation.mappers.toPresentation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardsFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val viewModel: CardsViewModel by viewModels()
    private val adapter by lazy { CardsAdapter() }


    override fun bind() { setupViewPager(); collectObservers() }


    /** ======================================= OBSERVERS ======================================= */
    private fun observeStates() = viewModel.state

    private fun collectObservers() = viewLifecycleOwner.launchAndRepeatOnStart {
        observeStates().collect { handleStates(group = it) }
    }
    /** ========================================================================================= */


    /** ======================================= HANDLERS ======================================== */
    private fun handleStates(group: CardsState) = with(receiver = group) {
        if (group.data.isNotEmpty())
            adapter.submitList(data.toPresentation())
    }
    /** ========================================================================================= */


    /** ========================================== AUX ========================================== */
    private fun setupViewPager() = with(receiver = binding.viewPager) {
        adapter = this@CardsFragment.adapter
        orientation = ORIENTATION_HORIZONTAL
    }
    /** ========================================================================================= */
}
