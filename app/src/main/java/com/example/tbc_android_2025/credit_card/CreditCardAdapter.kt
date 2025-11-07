package com.example.tbc_android_2025.credit_card

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tbc_android_2025.commons.Images
import com.example.tbc_android_2025.commons.StringUtils.DESERIALIZATION_DELIMITER
import com.example.tbc_android_2025.commons.Strings
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.example.tbc_android_2025.databinding.ItemCreditCardBinding as Binding

typealias CreditCardListAdapter = ListAdapter<CreditCard, CreditCardAdapter.CreditCardViewHolder>
typealias CreditCardDtos = List<CreditCardDto>

class CreditCardAdapter : CreditCardListAdapter(CreditCardDiffCallback) {

    private val moshi = Moshi.Builder().build()
    private val adapter = moshi.adapter<CreditCardDtos>(
        Types.newParameterizedType(
            List::class.java,
            CreditCardDto::class.java
        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, ignored: Int): CreditCardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = Binding.inflate(inflater, parent, false)
        return CreditCardViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: CreditCardViewHolder, position: Int) =
        holder.bind(creditCard = getItem(position))

    inner class CreditCardViewHolder(private val binding: Binding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(creditCard: CreditCard) = with(receiver = binding) {
            // 1️⃣ Load card background based on MII (first digit of the number)
            Glide.with(root.context)
                .load(getCardBackgroundResource(creditCard = creditCard))
                .into(creditCardImageView)

            // 2️⃣ Display the masked credit card number
//            numberTextView.text = CreditCardUtils.maskNumber(creditCard = creditCard)

            // 3️⃣ Display cardholder name
            nameTextView.text = creditCard.name

            val month: String = creditCard.expiry.monthValue.toString().padStart(2, '0')
            val year: String = (creditCard.expiry.year % 100).toString().padStart(2, '0')

            expiryTextView.text = expiryTextView.context.getString(
                Strings.expiry_input,
                buildString {
                    append(month)
                    append(DESERIALIZATION_DELIMITER)
                    append(year)
                }
            )
        }

        fun loadFromJson(rawResId: Int) {
            val inputStream = binding.root.resources.openRawResource(rawResId)
            val json = inputStream.bufferedReader().use { it.readText() }
            val dtos = adapter.fromJson(json).orEmpty()
            val cards = dtos.map { it.toDomain() }
            submitList(cards)
        }

        fun getCardBackgroundResource(creditCard: CreditCard): Int {
            CreditCardUtils.validateNumber(number = creditCard.number)
            val firstDigit = creditCard.number.first().digitToInt()

            val cardType = CreditCardType.entries.firstOrNull { it.mii == firstDigit }
                ?: throw UnsupportedCardNumberException(number = creditCard.number)

            return when (cardType) {
                CreditCardType.VISA -> Images.ic_visa_background
                CreditCardType.MASTERCARD -> Images.ic_mastercard_background
            }
        }
    }
}
