package com.example.tbc_android_2025.fragments

import androidx.core.os.bundleOf
import androidx.navigation.fragment.navArgs
import com.example.tbc_android_2025.commons.BaseFragment
import com.example.tbc_android_2025.commons.Colors
import com.example.tbc_android_2025.commons.Strings
import com.example.tbc_android_2025.databinding.FragmentUpdateExistingAddressBinding
import com.example.tbc_android_2025.extensions.popMessage
import com.example.tbc_android_2025.utils.UsefulStrings.ADDRESS_ID
import com.example.tbc_android_2025.utils.UsefulStrings.ADDRESS_LOCATION
import com.example.tbc_android_2025.utils.UsefulStrings.ADDRESS_SHORTCUT
import com.example.tbc_android_2025.utils.UsefulStrings.SUPPRESS_COMPILER_WARNING
import com.example.tbc_android_2025.utils.UsefulStrings.UPDATED_ADDRESS_REQUEST_KEY

@Suppress(SUPPRESS_COMPILER_WARNING)
private typealias UpdateExistingAddressBindingBase = BaseFragment<FragmentUpdateExistingAddressBinding>

class UpdateExistingAddressFragment : UpdateExistingAddressBindingBase(inflater = FragmentUpdateExistingAddressBinding::inflate) {

    private val args: UpdateExistingAddressFragmentArgs by navArgs()


    override fun bind() = Unit

    override fun listeners() {
        setListenerOnBackButton()
        setListenerOnUpdateExistingAddressButton()
    }


    private fun setListenerOnBackButton() = binding.backButton.setOnClickListener { navigateBack() }

    private fun setListenerOnUpdateExistingAddressButton() = binding.run {
        updateAddressButton.setOnClickListener {
            val shortcut = newShortcutEditText.text?.toString()?.trim()
            val location = newLocationEditText.text?.toString()?.trim()

            when {
                shortcut.isNullOrEmpty() || location.isNullOrEmpty() -> { showError() }
                else -> handleAddressUpdate(shortcut = shortcut, location = location)
            }
        }
    }

    private fun handleAddressUpdate(shortcut: String, location: String) {
        sendUpdatedAddressResult(shortcut = shortcut, location = location)
        showSuccess()
        navigateBack()
    }

    private fun sendUpdatedAddressResult(shortcut: String, location: String) {
        val resultBundle = bundleOf(
            ADDRESS_ID to args.addressId,
            ADDRESS_SHORTCUT to shortcut,
            ADDRESS_LOCATION to location
        )
        parentFragmentManager.setFragmentResult(UPDATED_ADDRESS_REQUEST_KEY, resultBundle)
    }

    private fun showError() = binding.root.popMessage(
        text = getString(Strings.all_fields_must_be_filled_in_label),
        color = Colors.red
    )

    private fun showSuccess() = binding.root.popMessage(
        text = getString(Strings.address_updated_successfully_label),
        color = Colors.light_green
    )
}
