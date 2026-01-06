package com.example.tbc_android_2025.presentation.common

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import android.view.LayoutInflater as Inflater
import android.view.ViewGroup as Container
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding as Binding

abstract class BaseAdapter<T : BaseAdapter.HasId<*>, VB : Binding>(
    private val inflater: ViewBindingInflater<VB>,
    private val onClick: ((T) -> Unit)? = null,
) : ListAdapter<T, BaseAdapter.BaseViewHolder<VB>>(BaseDiffItemCallback()) {


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


    /** TO BE IMPLEMENTED */
    abstract fun bind(binding: VB, item: T)


    interface HasId<I> {
        val id: I

        fun isContentTheSame(other: Any?): Boolean = this == other
    }


    class BaseViewHolder<VB : Binding>(private val binding: VB) : ViewHolder(binding.root) {

        fun bind(block: (VB) -> Unit) = block(binding)
    }


    /** AUX */
    private class BaseDiffItemCallback<T : HasId<*>> : ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
            oldItem.isContentTheSame(other = newItem)
    }
}
