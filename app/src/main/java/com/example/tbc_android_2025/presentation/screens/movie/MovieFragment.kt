package com.example.tbc_android_2025.presentation.screens.movie

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.tbc_android_2025.commons.Strings
import com.example.tbc_android_2025.databinding.FragmentMovieBinding as Binding
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import com.example.tbc_android_2025.presentation.extensions.format
import com.example.tbc_android_2025.presentation.extensions.launchAndRepeatOnStart
import com.example.tbc_android_2025.presentation.extensions.loadPoster
import com.example.tbc_android_2025.presentation.screens.commons.MovieModel
import com.example.tbc_android_2025.presentation.screens.movie.MovieContract.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

// TODO: trailer should be opened in youtube
// TODO: Implement embedded IMDB pirating for watching movies
// TODO: Hardcoded separator strings
@AndroidEntryPoint
class MovieFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val viewModel: MovieViewModel by viewModels()
    private val args: MovieFragmentArgs by navArgs()


    override fun bind() {
        bindMovie(movie = args.movie)
        collectObservers()
    }

    override fun listeners() {
        setListenerOnBackButton()
        setListenerOnWatchTrailer()
    }


    /** ===================================== PEAKY BINDERS ===================================== */
    private fun bindMovie(movie: MovieModel) {
        bindTitle(title = movie.title)
        bindPoster(url = movie.postersUrls.first())
        bindDescription(description = movie.description)
        formatReleaseDate(releaseDate = movie.releaseDate)
        bindDuration(duration = movie.duration)
        bindGenres(genres = movie.genres)
        bindLanguages(languages = movie.languages)
        bindAgeRating(ageRating = movie.ageRating)
        bindDirector(director = movie.director)
        bindCountry(country = movie.country)
        bindBudget(budget = movie.budget)
        bindBoxOfficeGross(boxOfficeGross = movie.boxOfficeGross)
    }

    private fun bindTitle(title: String) {
        binding.titleTextView.text = title
    }

    private fun bindPoster(url: String) = binding.posterImageView.loadPoster(url = url)

    private fun bindDescription(description: String) {
        binding.descriptionTextView.text = getString(Strings.description, description)
    }

    private fun formatReleaseDate(releaseDate: LocalDate) {
        binding.releaseDateTextView.text = getString(Strings.release_date, releaseDate.format())
    }

    private fun bindDuration(duration: String) {
        binding.durationTextView.text = getString(Strings.duration, duration)
    }

    private fun bindGenres(genres: List<MovieModel.Genre>) {
        binding.genresTextView.text =
            getString(Strings.genres, genres.joinToString(separator = ", "))
    }

    private fun bindLanguages(languages: List<MovieModel.Language>) {
        binding.languagesTextView.text =
            getString(Strings.languages, languages.joinToString(separator = ", "))
    }

    private fun bindAgeRating(ageRating: MovieModel.AgeRating) {
        binding.ageRatingTextView.text = getString(Strings.age_rating, ageRating)
    }

    private fun bindDirector(director: String) {
        binding.directorTextView.text = getString(Strings.director, director)
    }

    private fun bindCountry(country: String) {
        binding.countryTextView.text = getString(Strings.country, country)
    }

    private fun bindBudget(budget: String) {
        binding.budgetTextView.text = getString(Strings.budget, budget)
    }

    private fun bindBoxOfficeGross(boxOfficeGross: String) {
        binding.boxOfficeGrossTextView.text = getString(Strings.box_office_gross, boxOfficeGross)
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
            launch { viewModel.sideEffect.collectLatest { handleSideEffect(sideEffect = it) } }
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
