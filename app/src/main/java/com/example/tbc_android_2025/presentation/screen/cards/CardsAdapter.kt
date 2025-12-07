package com.example.tbc_android_2025.presentation.screen.cards

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tbc_android_2025.databinding.ItemCardBinding as Binding
import com.example.tbc_android_2025.presentation.extensions.loadImage
import com.example.tbc_android_2025.presentation.screen.cards.Cards.Card

class CardsAdapter :
    ListAdapter<Card, CardsAdapter.CardViewHolder>(object : ItemCallback<Card>() {
        override fun areItemsTheSame(oldCard: Card, newCard: Card) = oldCard.title == newCard.title
        override fun areContentsTheSame(oldCard: Card, newCard: Card) = oldCard == newCard
    }) {


    override fun onCreateViewHolder(parent: ViewGroup, ignored: Int) =
        CardViewHolder(
            binding = Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) =
        holder.bind(card = getItem(position))


    inner class CardViewHolder(private val binding: Binding) : ViewHolder(binding.root) {

        fun bind(card: Card) = with(receiver = card) {
            bindLocation(location = location)
            bindAltitude(altitude = altitude)
            bindTitle(title = title)
            bindImage(image = image)
            bindRating(stars = stars)
        }


        /** ====================================== BINDERS ====================================== */
        private fun bindLocation(location: String) {
            binding.locationTextView.text = location
        }

        private fun bindAltitude(altitude: Short) {
            binding.altitudeTextView.text = altitude.toString()
        }

        private fun bindTitle(title: String) {
            binding.titleTextView.text = title
        }

        private fun bindImage(image: String) = binding.cardImageView.loadImage(url = image)

        private fun bindRating(stars: Byte) {
            binding.ratingBar.rating = stars.toFloat()
        }
        /** ===================================================================================== */
    }
}
