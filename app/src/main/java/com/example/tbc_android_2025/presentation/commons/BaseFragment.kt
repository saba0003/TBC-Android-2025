package com.example.tbc_android_2025.presentation.commons

import android.os.Bundle
import android.view.LayoutInflater as Inflater
import android.view.View
import android.view.ViewGroup as Container
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding as Binding

abstract class BaseFragment<VB : Binding>(private val inflater: ViewBindingInflater<VB>) :
    Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!


    override fun onCreateView(inflater: Inflater, container: Container?, ignored: Bundle?): View? {
        _binding = inflater(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
        listeners()
    }


    /** setup */
    protected open fun bind() {}

    protected open fun listeners() {}

    protected open fun navigateBack() {
        findNavController().popBackStack()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
