package com.example.tbc_android_2025.item_wrapper.item

import androidx.recyclerview.widget.DiffUtil.ItemCallback

object ItemDiffCallback : ItemCallback<Item>() {

    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
        oldItem.field_id == newItem.field_id

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
        oldItem == newItem
}
