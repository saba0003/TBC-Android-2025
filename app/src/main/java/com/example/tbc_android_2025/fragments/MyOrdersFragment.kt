package com.example.tbc_android_2025.fragments

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbc_android_2025.viewmodels.MyOrdersViewModel
import com.example.tbc_android_2025.commons.BaseFragment
import com.example.tbc_android_2025.commons.Colors
import com.example.tbc_android_2025.databinding.FragmentMyOrdersBinding
import com.example.tbc_android_2025.order.OrderAdapter
import com.example.tbc_android_2025.utils.OrderStatus.ACTIVE
import com.example.tbc_android_2025.utils.OrderStatus.COMPLETED

typealias Binding = FragmentMyOrdersBinding
typealias BaseBinding = BaseFragment<Binding>

class MyOrdersFragment : BaseBinding(inflater = Binding::inflate) {

    // this also can be done by _adapter and overriding onDestroyView() similar to BaseFragment
    private val adapter: OrderAdapter by lazy { OrderAdapter() }
    private val viewModel: MyOrdersViewModel by viewModels()


    override fun bind() {
        setupRecycler()
        observeOrders()
        observeFilterStatus()
    }

    override fun listeners() {
        setListenerOnActiveButton()
        setListenerOnCompletedButton()
    }


    private fun setupRecycler() = binding.recyclerView.apply {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = this@MyOrdersFragment.adapter
    }

    private fun setListenerOnActiveButton() = binding.activeTextView.setOnClickListener {
        viewModel.setFilter(status = ACTIVE)
    }

    private fun setListenerOnCompletedButton() = binding.completedTextView.setOnClickListener {
        viewModel.setFilter(status = COMPLETED)
    }

    private fun observeOrders() = viewModel.filteredOrders.observe(viewLifecycleOwner) { orders ->
        adapter.submitList(orders)
    }

    private fun observeFilterStatus() = viewModel.filterStatus.observe(viewLifecycleOwner) { status ->
        val isActive = status == ACTIVE
        setSelectedFilter(isActive = isActive)
        underlineSelectedFilter(isActive = isActive)
    }

    private fun setSelectedFilter(isActive: Boolean) = with(receiver = binding) {
        activeTextView.isSelected = isActive
        completedTextView.isSelected = !isActive
    }

    private fun underlineSelectedFilter(isActive: Boolean) = with(receiver = binding) {
        val selected = underlineActiveTextView.context.getColor(Colors.light_green)
        val unselected = underlineCompletedTextView.context.getColor(Colors.amaranth)
        underlineActiveTextView.setBackgroundColor(if (isActive) selected else unselected)
        underlineCompletedTextView.setBackgroundColor(if (isActive) unselected else selected)
    }
}
