package com.example.tbc_android_2025.presentation.screens.movie_catalogue

import androidx.fragment.app.viewModels
import com.example.tbc_android_2025.databinding.FragmentMoviesCatalogueBinding as Binding
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import com.example.tbc_android_2025.presentation.extensions.launchAndRepeatOnStart
import com.example.tbc_android_2025.presentation.screens.movie_catalogue.MovieContract.State
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieCatalogueFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val viewModel: MovieCatalogueViewModel by viewModels()


    override fun bind() = collectObservers()


    /** ======================================= OBSERVERS ======================================= */
    private fun observeStates() = viewModel.state

    private fun collectObservers() {
        viewLifecycleOwner.launchAndRepeatOnStart {
            observeStates().collect { handleStates(group = it) }
        }
    }
    /** ========================================================================================= */


    /** ======================================= HANDLERS ======================================== */
    private fun handleStates(group: State) {}
    /** ========================================================================================= */
}
