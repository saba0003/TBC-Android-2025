package com.example.tbc_android_2025.fragments

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbc_android_2025.address.Address
import com.example.tbc_android_2025.address.AddressAdapter
import com.example.tbc_android_2025.commons.BaseFragment
import com.example.tbc_android_2025.commons.Drawables
import com.example.tbc_android_2025.commons.Strings
import com.example.tbc_android_2025.databinding.FragmentDeliveryAddressBinding
import com.example.tbc_android_2025.utils.UsefulStrings.SUPPRESS_COMPILER_WARNING

@Suppress(SUPPRESS_COMPILER_WARNING)
private typealias DeliveryAddressBindingBase = BaseFragment<FragmentDeliveryAddressBinding>

class DeliveryAddressFragment : DeliveryAddressBindingBase(inflater = FragmentDeliveryAddressBinding::inflate) {

    private val adapter: AddressAdapter by lazy { AddressAdapter() }
    private val addresses: List<Address> by lazy { seed() }


    override fun bind() = setup()

    override fun listeners() {
        setListenerOnBackButton()
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

    private fun setListenerOnBackButton() {
        binding.backButton.setOnClickListener {
            println("asda")
        }
    }

    private fun setListenerOnAddNewAddressButton() {
        binding.addNewAddressButton.setOnClickListener {

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
            fullLocation = getString(Strings.sample_address_label),
            icon = Drawables.ic_home
        )
    )
}
