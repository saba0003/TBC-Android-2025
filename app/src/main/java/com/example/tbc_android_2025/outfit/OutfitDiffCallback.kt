package com.example.tbc_android_2025.outfit

import androidx.recyclerview.widget.DiffUtil.ItemCallback

typealias OutfitItemCallback  = ItemCallback<Outfit>

object OutfitDiffCallback : OutfitItemCallback() {

    override fun areItemsTheSame(oldOutfit: Outfit, newOutfit: Outfit): Boolean =
        oldOutfit.id == newOutfit.id

    override fun areContentsTheSame(oldOutfit: Outfit, newOutfit: Outfit): Boolean =
        oldOutfit == newOutfit
}
