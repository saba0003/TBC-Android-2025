package com.example.tbc_android_2025.presentation.screen.movie

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.webkit.PermissionRequest
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.tbc_android_2025.common.Strings
import com.example.tbc_android_2025.databinding.FragmentMovieBinding as Binding
import com.example.tbc_android_2025.presentation.common.BaseFragment
import com.example.tbc_android_2025.presentation.extension.format
import com.example.tbc_android_2025.presentation.extension.launchAndRepeatOnStart
import com.example.tbc_android_2025.presentation.extension.loadPoster
import com.example.tbc_android_2025.presentation.screen.model.MovieModel
import com.example.tbc_android_2025.presentation.screen.movie.MovieContract.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import androidx.core.net.toUri

@AndroidEntryPoint
class MovieFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val viewModel: MovieViewModel by viewModels()
    private val args: MovieFragmentArgs by navArgs()


    override fun bind() {
        viewModel.onEvent(event = Event.Init(movie = args.movie))
        collectObservers()
    }

    override fun listeners() {
        setListenerOnBackButton()
        setListenerOnWatchTrailer()
    }


    /** ===================================== PEAKY BINDERS ===================================== */
    private fun bindMovie(movie: MovieModel) = with(receiver = movie) {
        bindTitle(title = title)
        bindPoster(url = postersUrls.first())
        bindDescription(description = description)
        formatReleaseDate(releaseDate = releaseDate)
        bindDuration(duration = duration)
        bindGenres(genres = genres)
        bindLanguages(languages = languages)
        bindAgeRating(ageRating = ageRating)
        bindDirector(director = director)
        bindCountry(country = country)
        bindBudget(budget = budget)
        bindBoxOfficeGross(boxOfficeGross = boxOfficeGross)
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
            getString(Strings.genres, genres.joinToString(separator = SEPARATOR))
    }

    private fun bindLanguages(languages: List<MovieModel.Language>) {
        binding.languagesTextView.text =
            getString(Strings.languages, languages.joinToString(separator = SEPARATOR))
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
        is State.Ready -> {
            bindMovie(movie = state.movie)
            setupMovieWebView(imdbId = state.movie.imdbId)
        }
    }

    private fun handleSideEffect(sideEffect: SideEffect) = when (sideEffect) {
        SideEffect.NavigateBack -> navigateBack()
        is SideEffect.PlayTrailer -> openYoutube(url = sideEffect.url)
    }

    /** ========================================================================================= */


    /** ========================================== AUX ========================================== */
    private fun openYoutube(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri()).apply { setPackage(BROWSER) }
        startActivity(intent)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupMovieWebView(imdbId: String) = with(receiver = binding.movieWebView) {
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.mediaPlaybackRequiresUserGesture = false
        settings.loadsImagesAutomatically = true
        webViewClient = WebViewClient()
        webChromeClient = provideWebChromeClient()
        loadMovie(imdbId = imdbId)
    }

    private fun provideWebChromeClient() = object : WebChromeClient() {

        private var customView: View? = null
        private var customViewCallback: CustomViewCallback? = null


        override fun onShowCustomView(view: View, callback: CustomViewCallback) {
            customView = view
            customViewCallback = callback
            (requireActivity().window.decorView as ViewGroup).addView(
                view,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

        override fun onHideCustomView() {
            customView?.let {
                (requireActivity().window.decorView as ViewGroup).removeView(it)
            }
            customViewCallback?.onCustomViewHidden()
            customView = null
        }

        override fun onPermissionRequest(request: PermissionRequest) =
            request.grant(request.resources)
    }

    private fun loadMovie(imdbId: String) {
        val html = requireContext()
            .assets
            .open(MOVIE_PLAYER)
            .bufferedReader()
            .use { it.readText() }
            .replace(oldValue = IMDB_ID, newValue = imdbId)

        binding.movieWebView.loadDataWithBaseURL(
            BASE_URL, html, MIME_TYPE, ENCODING, null
        )
    }
    /** ========================================================================================= */

    private companion object {
        const val SEPARATOR = ", "
        const val BROWSER = "com.android.chrome"
        const val MOVIE_PLAYER = "html/movie_player.html"
        const val IMDB_ID = "{{IMDB_ID}}"
        const val BASE_URL = "https://vsrc.su"
        const val MIME_TYPE = "text/html"
        const val ENCODING = "UTF-8"
    }
}
