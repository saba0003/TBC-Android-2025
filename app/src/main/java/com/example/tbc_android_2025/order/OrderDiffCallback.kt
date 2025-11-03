package com.example.tbc_android_2025.order

import androidx.recyclerview.widget.DiffUtil.ItemCallback

typealias OrderItemCallback  = ItemCallback<Order>

object OrderDiffCallback : OrderItemCallback() {
    override fun areItemsTheSame(order1: Order, order2: Order): Boolean = order1.id == order2.id
    override fun areContentsTheSame(order1: Order, order2: Order): Boolean = order1 == order2
}
