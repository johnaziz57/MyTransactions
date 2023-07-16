package com.example.mytransactoins.data.repo

import com.example.mytransactoins.domain.repo.TransactionRepo
import javax.inject.Inject

class TransactionRepoImpl @Inject constructor() : TransactionRepo {
    override fun getTransactions(): List<String> {
        return listOf("Transaction 1", "Transaction 2", "Transaction 3")
    }
}