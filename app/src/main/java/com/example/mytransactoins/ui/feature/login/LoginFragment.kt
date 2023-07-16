package com.example.mytransactoins.ui.feature.login

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
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

        viewModel.loginFormStateLiveData.observe(viewLifecycleOwner) {
            binding.loading.isVisible = false
            it.emailError.let { error -> binding.editTextEmail.error = error }
            it.passwordError.let { error -> binding.editTextPassword.error = error }
            if (it.isValid) {
                findNavController().navigateToNewTaskActivity(LoginFragmentDirections.actionLoginFragmentToTransactionActivity())
            }
        }

        with(binding) {
            buttonSignIn.setOnClickListener {
                loading.isVisible = true
                viewModel.login(
                    editTextEmail.text.toString(),
                    editTextPassword.text.toString()
                )
            }
        }
    }
}