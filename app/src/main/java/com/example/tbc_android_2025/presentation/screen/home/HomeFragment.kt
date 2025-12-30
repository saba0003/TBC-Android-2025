package com.example.tbc_android_2025.presentation.screen.home

import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbc_android_2025.presentation.common.Colors
import com.example.tbc_android_2025.databinding.FragmentHomeBinding as Binding
import com.example.tbc_android_2025.presentation.common.BaseFragment
import com.example.tbc_android_2025.presentation.extension.asString
import com.example.tbc_android_2025.presentation.extension.gone
import com.example.tbc_android_2025.presentation.extension.popMessage
import com.example.tbc_android_2025.presentation.extension.show
import com.example.tbc_android_2025.presentation.model.EquipmentCategoryModel
import com.example.tbc_android_2025.presentation.screen.home.HomeContract.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val viewModel: HomeViewModel by viewModels()

    private val adapter by lazy {
        HomeAdapter(onClick = { viewModel.onEvent(Event.OnEquipmentCategoryClick(equipmentCategory = it)) })
    }


    override fun bind() = setupRecycler()

    override fun listeners() {
        searchViewSetOnQueryTextListener()
        searchViewSetOnCloseListener()
    }

    override suspend fun collectObservers(): Unit = with(receiver = viewModel) {
        coroutineScope {
            launch { state.collect { handleStates(state = it) } }
            launch { sideEffect.collectLatest { handleSideEffects(sideEffect = it) } }
        }
    }


    /** ======================================= LISTENERS ======================================= */
    private fun searchViewSetOnQueryTextListener() =
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                showSuggestions()
                submitSearch(query = query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrBlank()) {
                    clearSuggestions()
                    submitSearch()
                }
                return false
            }
        })

    private fun searchViewSetOnCloseListener() = binding.searchView.setOnCloseListener {
        clearSuggestions()
        submitSearch(query = null)
        false
    }
    /** ========================================================================================= */


    /** ======================================= HANDLERS ======================================== */
    private fun handleStates(state: State) = adapter.submitList(state.filteredData)

    private fun handleSideEffects(sideEffect: SideEffect) = with(receiver = binding.root) {
        when (sideEffect) {
            is SideEffect.NavigateToEquipmentCategoryDetails -> navigateToEquipmentCategoryDetails(
                equipmentCategory = sideEffect.equipmentCategory
            )

            is SideEffect.ShowError -> popMessage(
                text = sideEffect.error.asString(context = context),
                color = Colors.amaranth
            )
        }
    }
    /** ========================================================================================= */


    /** ========================================== AUX ========================================== */
    private fun setupRecycler() = with(receiver = binding.suggestionsRecyclerView) {
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(requireContext())
        adapter = this@HomeFragment.adapter
    }

    private fun submitSearch(query: String? = null) =
        viewModel.onEvent(event = Event.OnSearch(query = query))

    private fun showSuggestions() = with(receiver = binding) {
        lineSeparatorView.show()
        suggestionsTextView.show()
        suggestionsRecyclerView.show()
    }

    private fun clearSuggestions() = with(receiver = binding) {
        lineSeparatorView.gone()
        suggestionsTextView.gone()
        suggestionsRecyclerView.gone()
    }

    private fun navigateToEquipmentCategoryDetails(equipmentCategory: EquipmentCategoryModel) {
        val direction = HomeFragmentDirections
            .actionHomeFragmentToEquipmentCategoryDetailsFragment(equipmentCategory = equipmentCategory)
        findNavController().navigate(directions = direction)
    }
    /** ========================================================================================= */
}
