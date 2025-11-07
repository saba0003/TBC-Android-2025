package com.example.tbc_android_2025.fragments

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.tbc_android_2025.commons.BaseFragment
import com.example.tbc_android_2025.credit_card.CreditCardAdapter
import com.example.tbc_android_2025.credit_card.CreditCardsViewModel
import com.example.tbc_android_2025.databinding.FragmentPaymentBinding

typealias BindingA = FragmentPaymentBinding
typealias BaseBindingA = BaseFragment<BindingA>

class PaymentFragment : BaseBindingA(inflater = BindingA::inflate) {

    private val viewModel: CreditCardsViewModel by viewModels()
    private val adapter by lazy { CreditCardAdapter() }

    override fun bind() {
        setupViewPager()
    }

    override fun listeners() {
        TODO(reason = "Not yet implemented")
    }

    override fun navigateBack() = requireActivity().moveTaskToBack(true)

    private fun setupViewPager() = binding.cardsViewPager.apply {
        adapter = this@PaymentFragment.adapter
        orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }
}
