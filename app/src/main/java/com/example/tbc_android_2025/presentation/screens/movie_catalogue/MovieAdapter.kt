package com.example.tbc_android_2025.presentation.screens.movie_catalogue

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tbc_android_2025.domain.models.MovieModel
import com.example.tbc_android_2025.databinding.ItemMovieBinding as Binding

class MovieAdapter :
    ListAdapter<MovieModel, MovieAdapter.MovieViewHolder>(object : ItemCallback<MovieModel>() {
        override fun areItemsTheSame(oldMovie: MovieModel, newMovie: MovieModel) =
            oldMovie.id == newMovie.id

        override fun areContentsTheSame(oldMovie: MovieModel, newMovie: MovieModel) =
            oldMovie == newMovie
    }) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieViewHolder(
            binding = Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(movie = getItem(position))


    inner class MovieViewHolder(private val binding: Binding) : ViewHolder(binding.root) {

        fun bind(movie: MovieModel) = with(receiver = movie) {

        }


        /** ====================================== BINDERS ====================================== */
        /** ===================================================================================== */
    }
}
