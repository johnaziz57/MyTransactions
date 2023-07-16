package com.example.mytransactoins.data.repo

import com.example.mytransactoins.domain.interactor.transaction.TransactionException
import com.example.mytransactoins.domain.model.NewResult
import com.example.mytransactoins.domain.repo.TransactionRepo
import javax.inject.Inject

class TransactionRepoImpl @Inject constructor() : TransactionRepo {
    override fun getTransactions(): NewResult<List<String>, TransactionException> {
        return NewResult.Success(listOf("Transaction 1", "Transaction 2", "Transaction 3"))
    }
}