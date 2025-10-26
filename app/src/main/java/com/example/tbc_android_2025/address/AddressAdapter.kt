package com.example.tbc_android_2025.address

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tbc_android_2025.databinding.ItemAddressBinding
import com.example.tbc_android_2025.utils.UsefulStrings.SUPPRESS_COMPILER_WARNING

@Suppress(SUPPRESS_COMPILER_WARNING)
private typealias AddressListAdapter = ListAdapter<Address, AddressAdapter.AddressViewHolder>

class AddressAdapter(
    private val onEditClicked: (Address) -> Unit,
    private val onLongPress: (Address) -> Unit
) : AddressListAdapter(AddressDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, ignored: Int): AddressViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAddressBinding.inflate(inflater, parent, false)
        return AddressViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        val address = getItem(position)
        holder.bind(address = address)
    }

    inner class AddressViewHolder(private val binding: ItemAddressBinding) : ViewHolder(binding.root) {

        fun bind(address: Address) = with(receiver = binding) {
            bindTextAndIcon(address = address)
            setupEditButton(address = address)
            setupLongPress(address = address)
            setupRadioButtonBehavior()
        }

        private fun ItemAddressBinding.bindTextAndIcon(address: Address) {
            shortcutTextView.text = address.shortcut
            fullAddressTextView.text = address.fullLocation
            addressImageView.setImageResource(address.icon)
        }

        private fun ItemAddressBinding.setupRadioButtonBehavior() {
            radioButtonAsCheckBox.setOnCheckedChangeListener { _, isChecked ->
                editTextView.isEnabled = isChecked
                editTextView.isClickable = isChecked
                editTextView.isFocusable = isChecked
                editTextView.alpha = if (isChecked) 1f else 0.5f
            }
        }

        private fun ItemAddressBinding.setupEditButton(address: Address) {
            editTextView.setOnClickListener { onEditClicked.invoke(address) }
        }

        private fun ItemAddressBinding.setupLongPress(address: Address) {
            root.setOnLongClickListener {
                onLongPress.invoke(address)
                true
            }
        }
    }
}
