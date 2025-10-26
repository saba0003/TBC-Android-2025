package com.example.tbc_android_2025.fragments

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbc_android_2025.address.Address
import com.example.tbc_android_2025.address.AddressAdapter
import com.example.tbc_android_2025.commons.BaseFragment
import com.example.tbc_android_2025.commons.Drawables
import com.example.tbc_android_2025.commons.Ids
import com.example.tbc_android_2025.commons.Strings
import com.example.tbc_android_2025.databinding.FragmentDeliveryAddressBinding
import com.example.tbc_android_2025.utils.UsefulStrings.LOCATION
import com.example.tbc_android_2025.utils.UsefulStrings.NEW_ADDRESS_REQUEST_KEY
import com.example.tbc_android_2025.utils.UsefulStrings.SHORTCUT
import com.example.tbc_android_2025.utils.UsefulStrings.SUPPRESS_COMPILER_WARNING

@Suppress(SUPPRESS_COMPILER_WARNING)
private typealias DeliveryAddressBindingBase = BaseFragment<FragmentDeliveryAddressBinding>

class DeliveryAddressFragment : DeliveryAddressBindingBase(inflater = FragmentDeliveryAddressBinding::inflate) {

    private val adapter: AddressAdapter by lazy { AddressAdapter() }
    private val addresses: MutableList<Address> by lazy { seed().toMutableList() }


    override fun bind() {
        setup()
        registerResultListener()
    }

    override fun listeners() {
        setListenerOnBackButton()
        setListenerOnAddNewAddressButton()
    }


    private fun setup() = binding.run {
        val recyclerView = addressRecycleView
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@DeliveryAddressFragment.adapter
//            setHasFixedSize(true)
        }
        adapter.submitList(addresses)
    }

    private fun setListenerOnBackButton() = binding.backButton.setOnClickListener {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    // 🔹 Trigger navigation to AddNewAddressFragment
    private fun setListenerOnAddNewAddressButton() =
        binding.addNewAddressButton.setOnClickListener {
            findNavController().navigate(
                Ids.action_deliveryAddressFragment_to_addNewAddressFragment
            )
        }

    // 🔹 Listen for results when user comes back
    private fun registerResultListener() {
        parentFragmentManager.setFragmentResultListener(NEW_ADDRESS_REQUEST_KEY, this) { _, bundle ->
            val shortcut = bundle.getString(SHORTCUT) ?: return@setFragmentResultListener
            val location = bundle.getString(LOCATION) ?: return@setFragmentResultListener

            val newAddress = Address(
                shortcut = shortcut,
                fullLocation = location,
                icon = Drawables.ic_home
            )

            addresses.add(0, newAddress)
            adapter.submitList(addresses.toList())
        }
    }

    private fun seed(): List<Address> = listOf(
        Address(
            shortcut = getString(Strings.sample_office_address_shortcut_label),
            fullLocation = getString(Strings.sample_address_label),
            icon = Drawables.ic_office
        ),
        Address(
            shortcut = getString(Strings.sample_home_address_shortcut_label),
            fullLocation = getString(Strings.sample_address_label)
        )
    )
}
