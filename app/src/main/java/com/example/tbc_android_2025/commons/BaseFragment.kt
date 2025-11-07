package com.example.tbc_android_2025.commons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup as Container
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

typealias ViewBindingInflater<VB> = (LayoutInflater, Container?, Boolean) -> VB

abstract class BaseFragment<VB : ViewBinding>(private val inflater: ViewBindingInflater<VB>) : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
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

    /** setup */
    protected abstract fun bind()

    protected abstract fun listeners()

    protected open fun navigateBack() = findNavController().popBackStack()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
