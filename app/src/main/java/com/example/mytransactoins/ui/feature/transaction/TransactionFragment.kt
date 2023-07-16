package com.example.mytransactoins.ui.feature.transaction

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mytransactoins.R
import com.example.mytransactoins.databinding.FragmentTransactionBinding
import com.example.mytransactoins.ui.feature.transaction.adapter.TransactionAdapter
import com.example.mytransactoins.ui.utils.navigateToNewTaskActivity
import com.example.mytransactoins.ui.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionFragment : Fragment(R.layout.fragment_transaction) {
    private val binding by viewBinding(FragmentTransactionBinding::bind)
    private val viewModel: TransactionViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.loadTransactions()
        val adapter = TransactionAdapter()
        binding.recyclerViewCollection.adapter = adapter

        binding.imageLogout.setOnClickListener {
            viewModel.logout()
            findNavController().navigateToNewTaskActivity(TransactionFragmentDirections.actionTransactionFragmentToMainActivity())
        }

        viewModel.transactionListLiveData.observe(viewLifecycleOwner) {
            if (it.isSuccessful.not()) return@observe
            adapter.setItems(it.data ?: emptyList())
        }
    }
}