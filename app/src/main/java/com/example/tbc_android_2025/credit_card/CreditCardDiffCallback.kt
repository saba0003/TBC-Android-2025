package com.example.tbc_android_2025.credit_card

import androidx.recyclerview.widget.DiffUtil.ItemCallback

typealias CreditCardItemCallback = ItemCallback<CreditCard>

object CreditCardDiffCallback : CreditCardItemCallback() {

    override fun areItemsTheSame(oldCreditCard: CreditCard, newCreditCard: CreditCard): Boolean =
        oldCreditCard.number == newCreditCard.number

    override fun areContentsTheSame(oldCreditCard: CreditCard, newCreditCard: CreditCard): Boolean =
        oldCreditCard == newCreditCard
}
