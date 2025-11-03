package com.example.tbc_android_2025.fragments

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbc_android_2025.commons.BaseFragment
import com.example.tbc_android_2025.commons.Colors
import com.example.tbc_android_2025.commons.Images
import com.example.tbc_android_2025.databinding.FragmentMyOrdersBinding
import com.example.tbc_android_2025.order.Order
import com.example.tbc_android_2025.order.OrderAdapter
import com.example.tbc_android_2025.utils.OrderStatus
import com.example.tbc_android_2025.utils.OrderStatus.ACTIVE
import com.example.tbc_android_2025.utils.OrderStatus.COMPLETED

typealias Binding = FragmentMyOrdersBinding
typealias BaseBinding = BaseFragment<Binding>

class MyOrdersFragment : BaseBinding(inflater = Binding::inflate) {

    private val adapter: OrderAdapter by lazy { OrderAdapter() }
    private val orders = mutableListOf<Order>()


    override fun bind() {
        setupRecycler()
        loadInitialOrders()
        filterByStatus(status = ACTIVE)
    }

    override fun listeners() {
        setListenerOnActiveButton()
        setListenerOnCompletedButton()
    }


    private fun setupRecycler() = binding.recyclerView.apply {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = this@MyOrdersFragment.adapter
    }

    private fun loadInitialOrders() {
        orders.addAll(elements = seed())
        adapter.submitList(orders.toList())
    }

    private fun setListenerOnActiveButton() =
        binding.activeTextView.setOnClickListener { filterByStatus(status = ACTIVE) }

    private fun setListenerOnCompletedButton() =
        binding.completedTextView.setOnClickListener { filterByStatus(status = COMPLETED) }

    private fun filterByStatus(status: OrderStatus) =
        adapter.submitList(orders.filter { it.status == status })

    private fun seed(): List<Order> = listOf(
        Order(
            imageRes = Images.order_1,
            colorRes = Colors.black,
            quantity = 5,
            status = ACTIVE,
            price = 280
        ),
        Order(
            imageRes = Images.order_2,
            colorRes = Colors.amaranth,
            quantity = 10,
            status = COMPLETED,
            price = 540
        ),
        Order(
            imageRes = Images.order_3,
            colorRes = Colors.light_green,
            quantity = 5,
            status = ACTIVE,
            price = 360
        ),
        Order(
            imageRes = Images.order_4,
            colorRes = Colors.purple_500,
            quantity = 5,
            status = COMPLETED,
            price = 750
        ),
        Order(
            imageRes = Images.order_5,
            colorRes = Colors.brown,
            quantity = 5,
            status = ACTIVE,
            price = 410
        ),
        Order(
            imageRes = Images.order_6,
            colorRes = Colors.viridian,
            quantity = 5,
            status = COMPLETED,
            price = 390
        ),
        Order(
            imageRes = Images.order_7,
            colorRes = Colors.white,
            quantity = 5,
            status = ACTIVE,
            price = 770
        ),
        Order(
            imageRes = Images.order_8,
            colorRes = Colors.black,
            quantity = 5,
            status = COMPLETED,
            price = 110
        )
    )
}
