package com.example.mytransactoins.ui.feature.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mytransactoins.R
import com.example.mytransactoins.databinding.FragmentLoginBinding
import com.example.mytransactoins.ui.utils.navigateToNewTaskActivity
import com.example.mytransactoins.ui.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels()
    private val binding by viewBinding(FragmentLoginBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.emailLiveData.observe(viewLifecycleOwner) {
            binding.editTextEmail.error = it.errorMessage
        }
        viewModel.passwordLiveData.observe(viewLifecycleOwner) {
            binding.editTextPassword.error = it.errorMessage
        }

        viewModel.loginResultLiveData.observe(viewLifecycleOwner) {
            if (it.isSuccessful.not()) return@observe
            findNavController().navigateToNewTaskActivity(LoginFragmentDirections.actionLoginFragmentToTransactionActivity())
        }

        with(binding) {
            buttonSignIn.setOnClickListener {
                viewModel.login(
                    editTextEmail.text.toString(),
                    editTextPassword.text.toString()
                )
            }
        }
    }
}