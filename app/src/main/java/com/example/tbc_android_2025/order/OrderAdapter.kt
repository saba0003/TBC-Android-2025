package com.example.tbc_android_2025.order

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tbc_android_2025.commons.Colors
import com.example.tbc_android_2025.commons.Strings
import com.example.tbc_android_2025.extensions.toPx
import com.example.tbc_android_2025.databinding.ItemOrderBinding as Binding

typealias OrderListAdapter = ListAdapter<Order, OrderAdapter.OrderViewHolder>

class OrderAdapter : OrderListAdapter(OrderDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, ignored: Int): OrderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = Binding.inflate(inflater, parent, false)
        return OrderViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = getItem(position)
        holder.bind(order = order)
    }

    inner class OrderViewHolder(private val binding: Binding) : ViewHolder(binding.root) {

        fun bind(order: Order) = with(receiver = binding) {
            setImage(order.imageRes)
            setOrderColor(colorRes = order.colorRes)
            setQuantity(quantity = order.quantity)
            setListenerOnStatusButton(order = order)
            setPrice(price = order.price)
        }

        private fun setListenerOnStatusButton(order: Order) = with(receiver = binding) {
            orderCompletedStatusButton.setOnClickListener {
                order.leaveReviewButtonIsVisible = !order.leaveReviewButtonIsVisible
                buyAgainButton.visibility =
                    if (order.leaveReviewButtonIsVisible) View.GONE else View.VISIBLE
                leaveReviewButton.visibility =
                    if (order.leaveReviewButtonIsVisible) View.VISIBLE else View.GONE
            }
        }

        private fun setImage(@DrawableRes imageRes: Int) = with(receiver = binding) {
            orderImageView.setImageResource(imageRes)
        }

        private fun setOrderColor(@ColorRes colorRes: Int) = with(receiver = binding.orderColorPalletImageView) {
            val drawable = background.mutate() as GradientDrawable
            val color = context.getColor(colorRes)
            drawable.setColor(color)

            // Determine stroke color based on brightness (luma)
            val isDark = ColorUtils.calculateLuminance(color) < 0.5
            val strokeColor = if (isDark) Colors.white else Colors.black
            drawable.setStroke(2.toPx(context), context.getColor(strokeColor))
        }

        private fun setQuantity(quantity: Int) = with(receiver = binding.orderQuantityTextView) {
            text = context.getString(Strings.order_quantity_label, quantity)
        }

        private fun setPrice(price: Int) = with(receiver = binding.orderPriceTextView) {
            text = context.getString(Strings.price_label, price)
        }
    }
}
