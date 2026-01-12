package com.example.tbc_android_2025.presentation.common.adapter

import android.view.ViewGroup as Container
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.example.tbc_android_2025.presentation.common.ViewBindingInflater

abstract class BaseListAdapter<T : HasId<*>, VB : ViewBinding>(
    private val inflater: ViewBindingInflater<VB>,
    private val onClick: ((T) -> Unit)? = null
) : ListAdapter<T, BaseViewHolder<VB>>(BaseDiffItemCallback()), Bindable<VB, T> {

    private val delegate = AdapterDelegate(inflater = inflater, onClick = onClick)


    override fun onCreateViewHolder(parent: Container, viewType: Int): BaseViewHolder<VB> =
        delegate.createViewHolder(parent = parent) { getItem(it) }

    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) =
        delegate.bind(holder = holder, item = getItem(position), binder = ::bind)
}
