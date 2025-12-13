package com.example.tbc_android_2025.presentation.screens.movie_catalogue

import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbc_android_2025.commons.Colors
import com.example.tbc_android_2025.commons.Strings
import com.example.tbc_android_2025.domain.exceptions.AppError
import com.example.tbc_android_2025.databinding.FragmentMoviesCatalogueBinding as Binding
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import com.example.tbc_android_2025.presentation.extensions.launchAndRepeatOnStart
import com.example.tbc_android_2025.presentation.extensions.popMessage
import com.example.tbc_android_2025.presentation.screens.movie_catalogue.MovieContract.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieCatalogueFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val viewModel: MovieCatalogueViewModel by viewModels()
    private val adapter by lazy { MovieAdapter() }


    override fun bind() { setupRecycler(); collectObservers() }


    /** ======================================= OBSERVERS ======================================= */
    private fun observeStates() = viewModel.state

    private fun observeSideEffects() = viewModel.sideEffect

    private fun collectObservers() {
        viewLifecycleOwner.launchAndRepeatOnStart {
            launch { observeStates().collect { handleStates(group = it) } }
            launch { observeSideEffects().collect { handleSideEffects(group = it) } }
        }
    }
    /** ========================================================================================= */


    /** ======================================= HANDLERS ======================================== */
    private fun handleStates(group: State) = with(receiver = group) {
        when (group) {
            is State.Success -> if (group.data.isNotEmpty()) adapter.submitList(group.data)
            is State.Error -> Unit
            is State.Loader -> Unit
        }
    }

    private fun handleSideEffects(group: SideEffect) = with(receiver = binding.root) {
        when (group) {
            is SideEffect.ShowError -> {
                val error = when (group.error) {
                    is AppError.Network -> ContextCompat.getString(context, Strings.error_network)
                    is AppError.Api -> ContextCompat.getString(context, Strings.error_api)
                    is AppError.State -> ContextCompat.getString(context, Strings.error_state)
                    is AppError.Unknown -> ContextCompat.getString(context, Strings.error_unknown)
                    is AppError.Message -> group.error.value
                }
                popMessage(text = error, color = Colors.amaranth)
            }
        }
    }
    /** ========================================================================================= */


    /** ========================================== AUX ========================================== */
    private fun setupRecycler() = with(receiver = binding.recyclerView) {
        adapter = this@MovieCatalogueFragment.adapter
        layoutManager = LinearLayoutManager(requireContext())
    }
    /** ========================================================================================= */
}
