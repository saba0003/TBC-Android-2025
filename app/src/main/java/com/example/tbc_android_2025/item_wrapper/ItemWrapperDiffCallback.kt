package com.example.tbc_android_2025.item_wrapper

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.example.tbc_android_2025.item_wrapper.item.ItemWrapper

object ItemWrapperDiffCallback : ItemCallback<ItemWrapper>() {

    override fun areItemsTheSame(oldItem: ItemWrapper, newItem: ItemWrapper): Boolean =
        oldItem.items.firstOrNull()?.field_id == newItem.items.firstOrNull()?.field_id

    override fun areContentsTheSame(oldItem: ItemWrapper, newItem: ItemWrapper): Boolean =
        oldItem == newItem
}
