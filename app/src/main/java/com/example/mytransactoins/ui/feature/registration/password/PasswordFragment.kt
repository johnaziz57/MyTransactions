package com.example.mytransactoins.ui.feature.registration.password

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mytransactoins.R
import com.example.mytransactoins.databinding.FragmentPasswordBinding
import com.example.mytransactoins.ui.utils.viewBinding

class PasswordFragment : Fragment(R.layout.fragment_password) {
    private val binding by viewBinding(FragmentPasswordBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonConfirm.setOnClickListener {
            findNavController().navigate(R.id.action_passwordFragment_to_transactionFragment)
        }
    }
}