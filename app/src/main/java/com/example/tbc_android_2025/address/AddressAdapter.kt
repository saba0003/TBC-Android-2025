package com.example.tbc_android_2025.address

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tbc_android_2025.databinding.ItemAddressBinding
import com.example.tbc_android_2025.utils.UsefulStrings.SUPPRESS_COMPILER_WARNING

@Suppress(SUPPRESS_COMPILER_WARNING)
private typealias AddressListAdapter = ListAdapter<Address, AddressAdapter.AddressViewHolder>

class AddressAdapter : AddressListAdapter(AddressDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, ignored: Int): AddressViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAddressBinding.inflate(inflater, parent, false)
        return AddressViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        val address = getItem(position)
        with(receiver = holder.binding) {
            shortcutTextView.text = address.shortcut
            fullAddressTextView.text = address.fullLocation
            addressImageView.setImageResource(address.icon)

            // Make "Edit" enabled/disabled depending on checkbox
            radioButtonAsCheckBox.setOnCheckedChangeListener { _, isChecked ->
                editTextView.isEnabled = isChecked
                editTextView.isClickable = isChecked
                editTextView.isFocusable = isChecked
                editTextView.alpha = if (isChecked) 1f else 0.5f // optional visual feedback
            }

//            // handle click
//            editTextView.setOnClickListener {
//                if (editTextView.isEnabled) {
//                    Toast.makeText(it.context, "Edit clicked for ${address.shortcut}", Toast.LENGTH_SHORT).show()
//                }
//            }
        }
    }

    inner class AddressViewHolder(val binding: ItemAddressBinding) : ViewHolder(binding.root)
}
