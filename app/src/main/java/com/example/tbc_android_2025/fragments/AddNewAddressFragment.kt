package com.example.tbc_android_2025.fragments

import androidx.core.os.bundleOf
import com.example.tbc_android_2025.commons.BaseFragment
import com.example.tbc_android_2025.commons.Colors
import com.example.tbc_android_2025.commons.Strings
import com.example.tbc_android_2025.databinding.FragmentAddNewAddressBinding
import com.example.tbc_android_2025.extensions.popMessage
import com.example.tbc_android_2025.utils.UsefulStrings.ADDRESS_LOCATION
import com.example.tbc_android_2025.utils.UsefulStrings.NEW_ADDRESS_REQUEST_KEY
import com.example.tbc_android_2025.utils.UsefulStrings.ADDRESS_SHORTCUT
import com.example.tbc_android_2025.utils.UsefulStrings.SUPPRESS_COMPILER_WARNING

@Suppress(SUPPRESS_COMPILER_WARNING)
private typealias AddNewAddressBindingBase = BaseFragment<FragmentAddNewAddressBinding>

class AddNewAddressFragment : AddNewAddressBindingBase(inflater = FragmentAddNewAddressBinding::inflate) {

    override fun bind() = Unit

    override fun listeners() {
        setListenerOnBackButton()
        setListenerOnAddNewAddressButton()
    }

    private fun setListenerOnBackButton() = binding.backButton.setOnClickListener { navigateBack() }

    private fun setListenerOnAddNewAddressButton() = binding.run {
        addNewAddressButton.setOnClickListener {
            val shortcut = shortcutEditText.text?.toString()?.trim()
            val location = locationEditText.text?.toString()?.trim()

            when {
                shortcut.isNullOrEmpty() || location.isNullOrEmpty() -> { showError() }
                else -> handleAddressAddition(shortcut = shortcut, location = location)
            }
        }
    }

    private fun handleAddressAddition(shortcut: String, location: String) {
        val resultBundle = bundleOf(
            ADDRESS_SHORTCUT to shortcut,
            ADDRESS_LOCATION to location
        )

        parentFragmentManager.setFragmentResult(NEW_ADDRESS_REQUEST_KEY, resultBundle)
        showSuccess()
        navigateBack()
    }

    private fun showError() = binding.root.popMessage(
        text = getString(Strings.all_fields_must_be_filled_in_label),
        color = Colors.red
    )

    private fun showSuccess() = binding.root.popMessage(
        text = getString(Strings.address_added_successfully_label),
        color = Colors.light_green
    )
}
