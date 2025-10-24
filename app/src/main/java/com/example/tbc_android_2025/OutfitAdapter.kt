package com.example.tbc_android_2025

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tbc_android_2025.commons.Strings
import com.example.tbc_android_2025.databinding.ItemGirlOutfitBinding

typealias RecyclerAdapter = Adapter<OutfitAdapter.OutfitViewHolder>

class OutfitAdapter(private val outfits: List<Outfit>) : RecyclerAdapter() {

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
            outfitPriceTextView.text = root.context.getString(Strings.price_label, outfit.price)
        }
    }

    override fun getItemCount() = outfits.size

    inner class OutfitViewHolder(val binding: ItemGirlOutfitBinding) : ViewHolder(binding.root)
}
