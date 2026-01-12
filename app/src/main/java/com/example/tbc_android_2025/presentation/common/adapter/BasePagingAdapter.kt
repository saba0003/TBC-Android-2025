package com.example.tbc_android_2025.presentation.common.adapter

import androidx.paging.PagingDataAdapter
import com.example.tbc_android_2025.presentation.common.ViewBindingInflater
import android.view.ViewGroup as Container
import androidx.viewbinding.ViewBinding as Binding

abstract class BasePagingAdapter<T : HasId<*>, VB : Binding>(
    private val inflater: ViewBindingInflater<VB>,
    private val onClick: ((T) -> Unit)? = null,
) : PagingDataAdapter<T, BaseViewHolder<VB>>(BaseDiffItemCallback()), Bindable<VB, T> {

    private val delegate = AdapterDelegate(inflater = inflater, onClick = onClick)


    override fun onCreateViewHolder(parent: Container, viewType: Int): BaseViewHolder<VB> =
        delegate.createViewHolder(parent = parent) { getItem(it) }

    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) {
        getItem(position)?.let { delegate.bind(holder = holder, item = it, binder = ::bind) }
    }
}
