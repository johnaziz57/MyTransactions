package com.example.mytransactoins.ui.feature.registration.password

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mytransactoins.R
import com.example.mytransactoins.databinding.FragmentPasswordBinding
import com.example.mytransactoins.ui.feature.registration.RegistrationViewModel
import com.example.mytransactoins.ui.utils.navigateToNewTaskActivity
import com.example.mytransactoins.ui.utils.viewBinding

class PasswordFragment : Fragment(R.layout.fragment_password) {
    private val binding by viewBinding(FragmentPasswordBinding::bind)
    private val viewModel: RegistrationViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            buttonConfirm.setOnClickListener {
                viewModel.submitPassword(
                    editTextPrimaryPassword.text.toString(),
                    editTextSecondaryPassword.text.toString()
                )
            }
            viewModel.primaryPasswordLiveData.observe(viewLifecycleOwner) {
                editTextPrimaryPassword.error = it.errorMessage
            }
            viewModel.secondaryPasswordLiveData.observe(viewLifecycleOwner) {
                editTextSecondaryPassword.error = it.errorMessage
            }
            viewModel.loginLiveData.observe(viewLifecycleOwner) {
                if (it.isSuccessful.not()) return@observe

                findNavController().navigateToNewTaskActivity(PasswordFragmentDirections.actionPasswordFragmentToTransactionActivity())
            }
        }
    }
}