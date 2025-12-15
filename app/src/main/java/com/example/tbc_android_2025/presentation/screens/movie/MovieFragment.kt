package com.example.tbc_android_2025.presentation.screens.movie

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.tbc_android_2025.databinding.FragmentMovieBinding as Binding
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import com.example.tbc_android_2025.presentation.extensions.launchAndRepeatOnStart
import com.example.tbc_android_2025.presentation.extensions.loadPoster
import com.example.tbc_android_2025.presentation.screens.commons.MovieModel
import com.example.tbc_android_2025.presentation.screens.movie.MovieContract.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val viewModel: MovieViewModel by viewModels()
    private val args: MovieFragmentArgs by navArgs()


    override fun bind() { bindMovie(movie = args.movie); collectObservers() }

    override fun listeners() { setListenerOnBackButton(); setListenerOnWatchTrailer() }


    /** ======================================== BINDERS ======================================== */
    private fun bindMovie(movie: MovieModel) {
        bindTitle(title = movie.title)
        bindPoster(url = movie.postersUrls.first())
        bindDescription(description = movie.description)
    }

    private fun bindTitle(title: String) { binding.titleTextView.text = title }

    private fun bindPoster(url: String) = binding.posterImageView.loadPoster(url = url)

    private fun bindDescription(description: String) {
        binding.descriptionTextView.text = description
    }
    /** ========================================================================================= */


    /** ======================================= LISTENERS ======================================= */
    private fun setListenerOnBackButton() = binding.backImageButton.setOnClickListener {
        viewModel.onEvent(event = Event.OnBackButtonPressed)
    }

    private fun setListenerOnWatchTrailer() = binding.watchTrailerButton.setOnClickListener {
        viewModel.onEvent(event = Event.OnWatchTrailer)
    }
    /** ========================================================================================= */


    /** ======================================= OBSERVERS ======================================= */
    private fun collectObservers() {
        viewLifecycleOwner.launchAndRepeatOnStart {
            launch { viewModel.state.collect { handleState(state = it) } }
            launch { viewModel.sideEffect.collect { handleSideEffect(sideEffect = it) } }
        }
    }


    /** ======================================= HANDLERS ======================================== */
    private fun handleState(state: State) = when (state) {
        State.Loading -> Unit
        is State.Ready -> bindMovie(movie = state.movie)
    }

    private fun handleSideEffect(sideEffect: SideEffect) = when (sideEffect) {
        SideEffect.NavigateBack -> navigateBack()
        is SideEffect.PlayTrailer -> {
            // TODO: later - open player / intent
        }
    }
    /** ========================================================================================= */
}
