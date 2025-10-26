package com.example.tbc_android_2025.fragments

import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.tbc_android_2025.commons.BaseFragment
import com.example.tbc_android_2025.commons.Colors
import com.example.tbc_android_2025.commons.Strings
import com.example.tbc_android_2025.databinding.FragmentAddNewAddressBinding
import com.example.tbc_android_2025.extensions.popMessage
import com.example.tbc_android_2025.utils.UsefulStrings.LOCATION
import com.example.tbc_android_2025.utils.UsefulStrings.NEW_ADDRESS_REQUEST_KEY
import com.example.tbc_android_2025.utils.UsefulStrings.SHORTCUT
import com.example.tbc_android_2025.utils.UsefulStrings.SUPPRESS_COMPILER_WARNING

@Suppress(SUPPRESS_COMPILER_WARNING)
private typealias AddNewAddressBindingBase = BaseFragment<FragmentAddNewAddressBinding>

class AddNewAddressFragment : AddNewAddressBindingBase(inflater = FragmentAddNewAddressBinding::inflate) {

    override fun bind() = Unit

    override fun listeners() {
        setListenerOnAddNewAddressButton()
        setListenerOnBackButton()
    }

    private fun setListenerOnAddNewAddressButton() = binding.run {
        addNewAddressButton.setOnClickListener {
            val shortcutInput = shortcutEditText.text?.toString()?.trim()
            val locationInput = locationEditText.text?.toString()?.trim()

            if (shortcutInput.isNullOrEmpty() || locationInput.isNullOrEmpty()) {
                root.popMessage(
                    text = getString(Strings.all_fields_must_be_filled_in_label),
                    color = Colors.red
                )
                return@setOnClickListener
            }

            // send result back to the previous fragment
            val resultBundle = bundleOf(
                SHORTCUT to shortcutInput,
                LOCATION to locationInput
            )

            parentFragmentManager.setFragmentResult(NEW_ADDRESS_REQUEST_KEY, resultBundle)

            // show success feedback before returning
            root.popMessage(
                text = getString(Strings.address_added_successfully_label),
                color = Colors.light_green
            )

            // navigate back to main fragment
            findNavController().popBackStack()
        }
    }

    private fun setListenerOnBackButton() =
        binding.backButton.setOnClickListener { findNavController().popBackStack() }
}
