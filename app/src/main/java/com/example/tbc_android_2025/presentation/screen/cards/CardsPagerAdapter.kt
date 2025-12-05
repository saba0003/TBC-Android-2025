package com.example.tbc_android_2025.presentation.screen.cards

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbc_android_2025.databinding.ItemCardBinding
import com.example.tbc_android_2025.presentation.extensions.loadImage

class CardsPagerAdapter :
    ListAdapter<Card, CardsPagerAdapter.CardViewHolder>(Diff) {

    object Diff : DiffUtil.ItemCallback<Card>() {
        override fun areItemsTheSame(old: Card, new: Card) =
            old.title == new.title

        override fun areContentsTheSame(old: Card, new: Card) =
            old == new
    }

    inner class CardViewHolder(private val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Card) = with(binding) {
            titleText.text = item.title
            locationText.text = item.location
            altitudeText.text = "Altitude: ${item.altitude} m"
            starsText.text = "★".repeat(item.stars.toInt())
            cardImage.loadImage(item.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CardViewHolder(ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) =
        holder.bind(getItem(position))
}
