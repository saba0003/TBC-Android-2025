package com.example.tbc_android_2025.presentation.commons

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import android.view.LayoutInflater as Inflater
import android.view.ViewGroup as Container
import androidx.viewbinding.ViewBinding as Binding

typealias ViewBindingInflater<VB> = (Inflater, Container?, Boolean) -> VB

abstract class BaseFragment<VB : Binding>(private val inflater: ViewBindingInflater<VB>) : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!


    override fun onCreateView(
        inflater: Inflater,
        container: Container?,
        ignored: Bundle?
    ): View? {
        _binding = this.inflater.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
        listeners()
    }

    protected open fun bind() {}

    protected abstract fun listeners()

    protected open fun observes() {}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
