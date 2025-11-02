package com.example.tbc_android_2025.fragments

import com.example.tbc_android_2025.commons.BaseFragment
import com.example.tbc_android_2025.databinding.FragmentTemplateBinding

typealias Binding = FragmentTemplateBinding
typealias BaseBinding = BaseFragment<FragmentTemplateBinding>

class TemplateFragment : BaseBinding(inflater = Binding::inflate) {

    override fun bind() {
        TODO(reason = "Not yet implemented")
    }

    override fun listeners() {
        TODO(reason = "Not yet implemented")
    }
}
