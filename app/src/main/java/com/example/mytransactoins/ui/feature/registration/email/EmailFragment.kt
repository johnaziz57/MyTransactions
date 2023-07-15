package com.example.mytransactoins.ui.feature.registration.email

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mytransactoins.R
import com.example.mytransactoins.databinding.FragmentEmailBinding
import com.example.mytransactoins.ui.utils.viewBinding

class EmailFragment : Fragment(R.layout.fragment_email) {
    private val binding by viewBinding(FragmentEmailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonRegister.setOnClickListener {
            findNavController().navigate(R.id.action_emailFragment_to_emailVerificationFragment)
        }
    }

}