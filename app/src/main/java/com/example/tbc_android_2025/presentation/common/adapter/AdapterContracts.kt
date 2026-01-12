package com.example.tbc_android_2025.presentation.common.adapter

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding as Binding

interface HasId<I> {
    val id: I
    fun isContentTheSame(other: Any?): Boolean = this == other
}

class BaseViewHolder<VB : Binding>(val binding: VB) : ViewHolder(binding.root)

internal interface Bindable<VB, T> {
    fun bind(binding: VB, item: T)
}

internal class BaseDiffItemCallback<T : HasId<*>> : ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem.isContentTheSame(other = newItem)
}
