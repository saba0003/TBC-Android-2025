package com.example.tbc_android_2025.presentation.screens.movie_catalogue

import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbc_android_2025.commons.Colors
import com.example.tbc_android_2025.commons.Strings
import com.example.tbc_android_2025.domain.exceptions.AppError
import com.example.tbc_android_2025.databinding.FragmentMoviesCatalogueBinding as Binding
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import com.example.tbc_android_2025.presentation.extensions.launchAndRepeatOnStart
import com.example.tbc_android_2025.presentation.extensions.popMessage
import com.example.tbc_android_2025.presentation.screens.movie_catalogue.MovieCatalogueContract.*
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

    private fun provideOnQueryTextListener() = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            submitSearch(query = query)
            binding.searchView.clearFocus() // hides keyboard
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            // optional: handle live search
            return true
        }
    }

    private fun setListenerOnBackButton() = binding.backImageButton.setOnClickListener { navigateBack() }
    /** ========================================================================================= */


    /** ======================================= OBSERVERS ======================================= */
    private fun collectObservers() {
        viewLifecycleOwner.launchAndRepeatOnStart {
            launch { viewModel.state.collect { handleStates(group = it) } }
            launch { viewModel.sideEffect.collectLatest { handleSideEffects(group = it) } }
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
                    AppError.Network -> ContextCompat.getString(context, Strings.error_network)
                    AppError.Api -> ContextCompat.getString(context, Strings.error_api)
                    AppError.State -> ContextCompat.getString(context, Strings.error_state)
                    AppError.Unknown -> ContextCompat.getString(context, Strings.error_unknown)
                    AppError.SearchQuery -> ContextCompat.getString(context, Strings.error_search_query_length_subminimal)
                    is AppError.Message -> group.error.value
                }
                popMessage(text = error, color = Colors.amaranth)
            }

            is SideEffect.NavigateToMovie -> {
                val direction =
                    MovieCatalogueFragmentDirections
                        .actionMovieCatalogueFragmentToMovieFragment(movie = group.movie)
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
