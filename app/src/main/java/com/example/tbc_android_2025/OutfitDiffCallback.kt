package com.example.tbc_android_2025

import androidx.recyclerview.widget.DiffUtil.ItemCallback

typealias OutfitItemCallback  = ItemCallback<Outfit>

object OutfitDiffCallback : OutfitItemCallback() {

    override fun areItemsTheSame(oldItem: Outfit, newItem: Outfit): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Outfit, newItem: Outfit): Boolean =
        oldItem == newItem
}
