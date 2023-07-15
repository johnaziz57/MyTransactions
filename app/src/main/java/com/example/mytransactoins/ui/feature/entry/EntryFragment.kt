package com.example.mytransactoins.ui.feature.entry

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mytransactoins.R
import com.example.mytransactoins.databinding.FragmentEntryBinding
import com.example.mytransactoins.ui.utils.navigateToNewTaskActivity
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
        findNavController().navigateToNewTaskActivity(EntryFragmentDirections.actionEntryFragmentToTransactionActivity())
    }

    private fun navigateToRegistration() {
        findNavController().navigate(EntryFragmentDirections.actionEntryFragmentToRegistrationActivity())
    }
}