package com.example.tbc_android_2025.outfit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tbc_android_2025.commons.Strings
import com.example.tbc_android_2025.databinding.ItemGirlOutfitBinding
import com.example.tbc_android_2025.utils.UsefulStrings.SUPPRESS_COMPILER_WARNING

@Suppress(SUPPRESS_COMPILER_WARNING)
private typealias OutfitListAdapter = ListAdapter<Outfit, OutfitAdapter.OutfitViewHolder>

class OutfitAdapter : OutfitListAdapter(OutfitDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, ignored: Int): OutfitViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemGirlOutfitBinding.inflate(inflater, parent, false)
        return OutfitViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: OutfitViewHolder, position: Int) {
        val outfit = getItem(position)
        with(receiver = holder.binding) {
            outfitImage.setImageResource(outfit.image)
            outfitDescriptionTextView.text = outfit.label
            outfitPriceTextView.text = root.context.getString(Strings.price_label, outfit.price)
        }
    }

    inner class OutfitViewHolder(val binding: ItemGirlOutfitBinding) : ViewHolder(binding.root)
}
