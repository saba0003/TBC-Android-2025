package com.example.tbc_android_2025.address

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.example.tbc_android_2025.utils.UsefulStrings.SUPPRESS_COMPILER_WARNING

@Suppress(SUPPRESS_COMPILER_WARNING)
private typealias AddressItemCallback  = ItemCallback<Address>

object AddressDiffCallback : AddressItemCallback() {

    override fun areItemsTheSame(oldAddress: Address, newAddress: Address): Boolean =
        oldAddress.id == newAddress.id

    override fun areContentsTheSame(oldAddress: Address, newAddress: Address): Boolean =
        oldAddress == newAddress
}
