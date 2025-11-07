package com.example.tbc_android_2025.fragments

import com.example.tbc_android_2025.commons.BaseFragment
import com.example.tbc_android_2025.databinding.FragmentPaymentBinding

typealias BindingA = FragmentPaymentBinding
typealias BaseBindingA = BaseFragment<BindingA>

class PaymentFragment : BaseBindingA(inflater = BindingA::inflate) {

    override fun bind() {
        TODO(reason = "Not yet implemented")
    }

    override fun listeners() {
        TODO(reason = "Not yet implemented")
    }

    override fun navigateBack() = requireActivity().moveTaskToBack(true)
}
