package com.example.mytransactoins.ui.feature.entry

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.mytransactoins.R
import com.example.mytransactoins.databinding.FragmentEntryBinding
import com.example.mytransactoins.ui.utils.viewBinding

class EntryFragment : Fragment(R.layout.fragment_entry) {

    private val binding by viewBinding(FragmentEntryBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonLogin.setOnClickListener {
            navigateToTransactions()
        }
        binding.buttonRegister.setOnClickListener {
            navigateToRegistration()
        }
    }

    private fun navigateToTransactions() {
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.entryFragment, true)
            .build()
        val extras = ActivityNavigator.Extras.Builder()
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            .build()

        findNavController().navigate(
            EntryFragmentDirections.actionEntryFragmentToTransactionActivity(),
            extras
        )
    }

    private fun navigateToRegistration() {
        findNavController().navigate(EntryFragmentDirections.actionEntryFragmentToRegistrationActivity())
    }
}