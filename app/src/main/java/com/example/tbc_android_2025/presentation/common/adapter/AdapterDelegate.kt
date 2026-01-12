package com.example.tbc_android_2025.presentation.common.adapter

import android.view.LayoutInflater as Inflater
import android.view.ViewGroup as Container
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.example.tbc_android_2025.presentation.common.ViewBindingInflater
import androidx.viewbinding.ViewBinding as Binding

internal class AdapterDelegate<T, VB : Binding>(
    private val inflater: ViewBindingInflater<VB>,
    private val onClick: ((T) -> Unit)?
) {

    internal fun createViewHolder(parent: Container, getItemAt: (Int) -> T?): BaseViewHolder<VB> {
        val binding = inflater(Inflater.from(parent.context), parent, false)
        val holder = BaseViewHolder(binding = binding)

        onClick?.let { listener ->
            binding.root.setOnClickListener {
                holder.bindingAdapterPosition
                    .takeIf { it != NO_POSITION }
                    ?.let(block = getItemAt)
                    ?.let(block = listener)
            }
        }

        return holder
    }

    internal fun bind(holder: BaseViewHolder<VB>, item: T, binder: (VB, T) -> Unit) =
        binder(holder.binding, item)
}
