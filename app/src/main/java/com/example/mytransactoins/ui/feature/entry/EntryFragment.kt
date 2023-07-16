package com.example.mytransactoins.ui.feature.entry

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
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
        binding.progressBarLoading.isVisible = true
        binding.groupButtons.isVisible = false

        viewModel.isLoggedInLiveData.observe(viewLifecycleOwner) {
            if (it.isSuccessful) {
                if (it.data == true) {
                    findNavController().navigateToNewTaskActivity(EntryFragmentDirections.actionEntryFragmentToTransactionActivity())
                } else {
                    stopLoading()
                }
            } else {
                Toast.makeText(
                    context,
                    it.errorMessage ?: "Something went wrong",
                    Toast.LENGTH_LONG
                ).show()
                stopLoading()
            }

        }

        binding.buttonLogin.setOnClickListener {
            findNavController().navigate(EntryFragmentDirections.actionEntryFragmentToLoginFragment())
        }
        binding.buttonRegister.setOnClickListener {
            findNavController().navigate(EntryFragmentDirections.actionEntryFragmentToRegistrationActivity())
        }

        view.postDelayed({ viewModel.isLoggedIn() }, 1000)
    }

    private fun stopLoading() {
        binding.groupButtons.isVisible = true
        binding.progressBarLoading.isVisible = false
    }
}