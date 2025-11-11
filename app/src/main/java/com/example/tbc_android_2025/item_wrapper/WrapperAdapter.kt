package com.example.tbc_android_2025.item_wrapper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tbc_android_2025.databinding.WrapperItemBinding
import com.example.tbc_android_2025.item_wrapper.item.ItemAdapter
import com.example.tbc_android_2025.item_wrapper.item.ItemWrapper

class WrapperAdapter : ListAdapter<ItemWrapper, WrapperAdapter.WrapperViewHolder>(ItemWrapperDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        WrapperViewHolder(WrapperItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: WrapperViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class WrapperViewHolder(private val binding: WrapperItemBinding) : ViewHolder(binding.root) {
        private val innerAdapter = ItemAdapter()

        init {
            binding.innerRecyclerView.adapter = innerAdapter
            binding.innerRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)
            binding.innerRecyclerView.isNestedScrollingEnabled = false
        }

        fun bind(wrapper: ItemWrapper) {
            innerAdapter.submitList(wrapper.items)
        }
    }
}
