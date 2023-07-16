package com.example.mytransactoins.ui.feature.registration.email

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mytransactoins.R
import com.example.mytransactoins.databinding.FragmentEmailBinding
import com.example.mytransactoins.ui.feature.registration.RegistrationViewModel
import com.example.mytransactoins.ui.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmailFragment : Fragment(R.layout.fragment_email) {
    private val binding by viewBinding(FragmentEmailBinding::bind)
    private val viewModel: RegistrationViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonRegister.setOnClickListener {

            viewModel.submitEmail(binding.editTextEmail.text.toString())

        }
        viewModel.validateEmailLiveData.observe(viewLifecycleOwner) {
            if (it.isSuccessful) {
                findNavController().navigate(R.id.action_emailFragment_to_emailVerificationFragment)
            } else {
                binding.editTextEmail.error = it.errorMessage
            }
        }
    }
}