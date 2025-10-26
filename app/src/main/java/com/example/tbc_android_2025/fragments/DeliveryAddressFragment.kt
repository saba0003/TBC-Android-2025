package com.example.tbc_android_2025.fragments

import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbc_android_2025.address.Address
import com.example.tbc_android_2025.address.AddressAdapter
import com.example.tbc_android_2025.commons.BaseFragment
import com.example.tbc_android_2025.commons.Drawables
import com.example.tbc_android_2025.commons.Ids
import com.example.tbc_android_2025.commons.Strings
import com.example.tbc_android_2025.databinding.FragmentDeliveryAddressBinding
import com.example.tbc_android_2025.utils.UsefulStrings.ADDRESS_ID
import com.example.tbc_android_2025.utils.UsefulStrings.ADDRESS_LOCATION
import com.example.tbc_android_2025.utils.UsefulStrings.NEW_ADDRESS_REQUEST_KEY
import com.example.tbc_android_2025.utils.UsefulStrings.ADDRESS_SHORTCUT
import com.example.tbc_android_2025.utils.UsefulStrings.SUPPRESS_COMPILER_WARNING
import com.example.tbc_android_2025.utils.UsefulStrings.UPDATED_ADDRESS_REQUEST_KEY

@Suppress(SUPPRESS_COMPILER_WARNING)
private typealias DeliveryAddressBindingBase = BaseFragment<FragmentDeliveryAddressBinding>

class DeliveryAddressFragment : DeliveryAddressBindingBase(inflater = FragmentDeliveryAddressBinding::inflate) {

    private val adapter: AddressAdapter by lazy {
        AddressAdapter(
            onEditClicked = { address ->
                val action = DeliveryAddressFragmentDirections
                    .actionDeliveryAddressFragmentToUpdateExistingAddressFragment(address.id)
                findNavController().navigate(action)
            },
            onLongPress = { address ->
                showDeleteConfirmationDialog(address)
            }
        )
    }
    private val addresses: MutableList<Address> by lazy { seed().toMutableList() }


    override fun bind() {
        setup()
        registerResultListener()
        registerUpdateListener()
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
                resId = Ids.action_deliveryAddressFragment_to_addNewAddressFragment
            )
        }

    // 🔹 Listen for results when user comes back
    private fun registerResultListener() {
        parentFragmentManager.setFragmentResultListener(
            NEW_ADDRESS_REQUEST_KEY,
            this
        ) { _, bundle ->
            val shortcut = bundle.getString(ADDRESS_SHORTCUT) ?: return@setFragmentResultListener
            val location = bundle.getString(ADDRESS_LOCATION) ?: return@setFragmentResultListener

            val newAddress = Address(
                shortcut = shortcut,
                fullLocation = location,
                icon = Drawables.ic_home
            )

            addresses.add(0, newAddress)
            adapter.submitList(addresses.toList()) {
                binding.addressRecycleView.scrollToPosition(0) // now executes after diff completes
            }
        }
    }

    // 🔹 Listener for updated addresses
    private fun registerUpdateListener() {
        parentFragmentManager.setFragmentResultListener(
            UPDATED_ADDRESS_REQUEST_KEY,
            this
        ) { _, bundle ->
            val addressId = bundle.getInt(ADDRESS_ID, -1)
            val newShortcut = bundle.getString(ADDRESS_SHORTCUT) ?: return@setFragmentResultListener
            val newLocation = bundle.getString(ADDRESS_LOCATION) ?: return@setFragmentResultListener

            val index = addresses.indexOfFirst { it.id == addressId }
            if (index != -1) {
                val updated = addresses[index].copy(
                    shortcut = newShortcut,
                    fullLocation = newLocation
                )
                addresses[index] = updated
                adapter.submitList(addresses.toList())
            }
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

    private fun showDeleteConfirmationDialog(address: Address) {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(Strings.confirm_delete_title))
            .setMessage(getString(Strings.confirm_delete_message))
            .setPositiveButton(getString(Strings.delete_label)) { _, _ ->
                deleteAddress(address)
            }
            .setNegativeButton(getString(Strings.cancel_label), null)
            .show()
    }

    private fun deleteAddress(address: Address) {
        addresses.remove(address)
        adapter.submitList(addresses.toList())
    }
}
