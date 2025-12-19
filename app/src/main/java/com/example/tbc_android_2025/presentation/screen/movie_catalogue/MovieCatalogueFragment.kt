package com.example.tbc_android_2025.presentation.screen.movie_catalogue

import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbc_android_2025.common.Colors
import com.example.tbc_android_2025.common.Strings
import com.example.tbc_android_2025.domain.exception.AppError
import com.example.tbc_android_2025.databinding.FragmentMoviesCatalogueBinding as Binding
import com.example.tbc_android_2025.presentation.common.BaseFragment
import com.example.tbc_android_2025.presentation.extension.launchAndRepeatOnStart
import com.example.tbc_android_2025.presentation.extension.popMessage
import com.example.tbc_android_2025.presentation.screen.movie_catalogue.MovieCatalogueContract.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieCatalogueFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val viewModel: MovieCatalogueViewModel by viewModels()
    private val adapter by lazy {
        MovieCatalogueAdapter { viewModel.onEvent(event = Event.OnMovieClicked(movie = it)) }
    }


    override fun bind() { setupRecycler(); collectObservers() }

    override fun listeners() { setListenerOnSearchBar(); setListenerOnBackButton() }

    override fun navigateBack() = requireActivity().finish()


    /** ======================================= LISTENERS ======================================= */
    private fun setListenerOnSearchBar() =
        binding.searchView.setOnQueryTextListener(provideOnQueryTextListener())

    // TODO: Debouncer can be added
    private fun provideOnQueryTextListener() = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            submitSearch(query = query)
            binding.searchView.clearFocus()
            return true
        }

        override fun onQueryTextChange(newText: String?) = false
    }

    private fun setListenerOnBackButton() = binding.backImageButton.setOnClickListener { navigateBack() }
    /** ========================================================================================= */


    /** ======================================= OBSERVERS ======================================= */
    private fun collectObservers() {
        viewLifecycleOwner.launchAndRepeatOnStart {
            launch { viewModel.state.collect { handleStates(state = it) } }
            launch { viewModel.sideEffect.collectLatest { handleSideEffects(sideEffect = it) } }
        }
    }
    /** ========================================================================================= */


    /** ======================================= HANDLERS ======================================== */
    private fun handleStates(state: State) = with(receiver = state) {
        when (state) {
            is State.Success -> if (state.data.isNotEmpty()) adapter.submitList(state.data)
            is State.Error -> Unit
            is State.Loader -> Unit
        }
    }

    private fun handleSideEffects(sideEffect: SideEffect) = with(receiver = binding.root) {
        when (sideEffect) {
            is SideEffect.ShowError -> {
                val error = when (sideEffect.error) {
                    AppError.Network -> ContextCompat.getString(context, Strings.error_network)
                    AppError.Api -> ContextCompat.getString(context, Strings.error_api)
                    AppError.State -> ContextCompat.getString(context, Strings.error_state)
                    AppError.Unknown -> ContextCompat.getString(context, Strings.error_unknown)
                    AppError.SearchQuery -> ContextCompat.getString(context, Strings.error_search_query_length_subminimal)
                    is AppError.Message -> sideEffect.error.value
                }
                popMessage(text = error, color = Colors.amaranth)
            }

            is SideEffect.NavigateToMovie -> {
                val direction =
                    MovieCatalogueFragmentDirections
                        .actionMovieCatalogueFragmentToMovieFragment(movie = sideEffect.movie)
                findNavController().navigate(directions = direction)
            }
        }
    }
    /** ========================================================================================= */


    /** ========================================== AUX ========================================== */
    private fun setupRecycler() = with(receiver = binding.recyclerView) {
        adapter = this@MovieCatalogueFragment.adapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    private fun submitSearch(query: String?) =
        query?.let { viewModel.onEvent(event = Event.OnGetMovieModelsByTitle(title = it)) }
    /** ========================================================================================= */
}
