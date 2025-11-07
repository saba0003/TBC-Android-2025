package com.example.tbc_android_2025.credit_card

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbc_android_2025.databinding.ItemCreditCardBinding as Binding

typealias CreditCardListAdapter = ListAdapter<CreditCard, CreditCardAdapter.CreditCardViewHolder>

class CreditCardAdapter : CreditCardListAdapter(CreditCardDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, ignored: Int): CreditCardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = Binding.inflate(inflater, parent, false)
        return CreditCardViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: CreditCardViewHolder, position: Int) =
        holder.bind(creditCard = getItem(position))

    inner class CreditCardViewHolder(private val binding: Binding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(creditCard: CreditCard) {

        }
    }
}
