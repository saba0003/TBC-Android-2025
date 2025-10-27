package com.example.tbc_android_2025.fragments

import androidx.navigation.fragment.findNavController
import com.example.tbc_android_2025.commons.BaseFragment
import com.example.tbc_android_2025.databinding.FragmentConfigurationBinding

class ConfigurationFragment : BaseFragment<FragmentConfigurationBinding>(inflater = FragmentConfigurationBinding::inflate) {

    override fun bind() = Unit

    override fun listeners() = setListenerOnStartGame()

    private fun setListenerOnStartGame() = binding.run {
        startGameButton.setOnClickListener {
            val selectedDimension = when (dimensionRadioGroup.checkedRadioButtonId) {
                radio3x3.id -> 3
                radio4x4.id -> 4
                radio5x5.id -> 5
                else -> 3
            }

            val action =
                ConfigurationFragmentDirections.actionConfigurationFragmentToGameFragment(gridSize = selectedDimension)
            findNavController().navigate(directions = action)
        }
    }
}
