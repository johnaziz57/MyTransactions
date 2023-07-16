package com.example.mytransactoins.ui.feature.entry

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mytransactoins.R
import com.example.mytransactoins.databinding.FragmentEntryBinding
import com.example.mytransactoins.ui.utils.navigateToNewTaskActivity
import com.example.mytransactoins.ui.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EntryFragment : Fragment(R.layout.fragment_entry) {

    private val binding by viewBinding(FragmentEntryBinding::bind)
    private val viewModel: EntryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (viewModel.isLoggedIn()) {
            findNavController().navigateToNewTaskActivity(EntryFragmentDirections.actionEntryFragmentToTransactionActivity())
        }

        binding.buttonLogin.setOnClickListener {
            findNavController().navigate(EntryFragmentDirections.actionEntryFragmentToLoginFragment())
        }
        binding.buttonRegister.setOnClickListener {
            navigateToRegistration()
        }
    }

    private fun navigateToRegistration() {
        findNavController().navigate(EntryFragmentDirections.actionEntryFragmentToRegistrationActivity())
    }
}