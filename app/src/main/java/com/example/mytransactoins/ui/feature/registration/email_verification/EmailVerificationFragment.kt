package com.example.mytransactoins.ui.feature.registration.email_verification

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mytransactoins.R
import com.example.mytransactoins.databinding.FragmentEmailVerificationBinding
import com.example.mytransactoins.ui.utils.viewBinding

class EmailVerificationFragment : Fragment(R.layout.fragment_email_verification) {
    private val binding by viewBinding(FragmentEmailVerificationBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonConfirm.setOnClickListener {
            findNavController().navigate(R.id.action_emailVerificationFragment_to_passwordFragment)
        }
    }
}