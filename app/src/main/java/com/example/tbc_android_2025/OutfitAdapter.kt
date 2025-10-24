package com.example.tbc_android_2025

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tbc_android_2025.databinding.ItemGirlOutfitBinding

class OutfitAdapter(
    private val outfits: List<Outfit>
) : RecyclerView.Adapter<OutfitAdapter.OutfitViewHolder>() {

    inner class OutfitViewHolder(val binding: ItemGirlOutfitBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OutfitViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemGirlOutfitBinding.inflate(inflater, parent, false)
        return OutfitViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: OutfitViewHolder, position: Int) {
        val outfit = outfits[position]
        with(receiver = holder.binding) {
            outfitImage.setImageResource(outfit.image)
            outfitDescriptionTextView.text = outfit.label
            outfitPriceTextView.text = "$${outfit.price}"
        }
    }

    override fun getItemCount() = outfits.size
}
