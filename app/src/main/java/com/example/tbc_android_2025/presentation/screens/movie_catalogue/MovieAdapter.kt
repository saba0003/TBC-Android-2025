package com.example.tbc_android_2025.presentation.screens.movie_catalogue

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.example.tbc_android_2025.presentation.commons.BaseAdapter
import com.example.tbc_android_2025.presentation.extensions.loadImage
import com.example.tbc_android_2025.databinding.ItemMovieBinding as Binding

class MovieAdapter : BaseAdapter<MovieModel, Binding>(
    inflater = Binding::inflate,
    diffCallback = object : ItemCallback<MovieModel>() {
        override fun areItemsTheSame(oldMovie: MovieModel, newMovie: MovieModel) =
            oldMovie.id == newMovie.id

        override fun areContentsTheSame(oldMovie: MovieModel, newMovie: MovieModel) =
            oldMovie == newMovie
    }) {


    override fun bind(binding: Binding, item: MovieModel) {
        binding.posterImageView.loadImage(url = item.postersUrls.first())
        binding.titleTextView.text = item.title
        binding.descriptionTextView.text = item.description
    }

}
