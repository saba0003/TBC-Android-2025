package com.example.tbc_android_2025.presentation.common

import android.view.LayoutInflater as Inflater
import android.view.ViewGroup as Container
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding as Binding

abstract class BaseAdapter<T, VB : Binding>(
    private val inflater: ViewBindingInflater<VB>,
    private val onClick: ((T) -> Unit)? = null,
    diffCallback: ItemCallback<T>,
) : ListAdapter<T, BaseAdapter.BaseViewHolder<VB>>(diffCallback) {


    override fun onCreateViewHolder(container: Container, ignored: Int): BaseViewHolder<VB> {
        val binding = inflater(Inflater.from(container.context), container, false)
        val holder = BaseViewHolder(binding = binding)

        onClick?.let { listener ->
            binding.root.setOnClickListener {
                holder.bindingAdapterPosition
                    .takeIf { it != NO_POSITION }
                    ?.let { position -> listener(getItem(position)) }
            }
        }

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) =
        holder.bind { bind(binding = it, item = getItem(position)) }


    abstract fun bind(binding: VB, item: T)


    class BaseViewHolder<VB : Binding>(private val binding: VB) : ViewHolder(binding.root) {

        fun bind(block: (VB) -> Unit) = block(binding)
    }
}
