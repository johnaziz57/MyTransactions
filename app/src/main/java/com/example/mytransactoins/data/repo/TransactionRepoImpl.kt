package com.example.mytransactoins.data.repo

import com.example.mytransactoins.domain.interactor.transaction.TransactionException
import com.example.mytransactoins.domain.model.Result
import com.example.mytransactoins.domain.repo.TransactionRepo
import javax.inject.Inject

class TransactionRepoImpl @Inject constructor() : TransactionRepo {
    override fun getTransactions(): Result<List<String>, TransactionException> {
        return Result.Success(listOf("Transaction 1", "Transaction 2", "Transaction 3"))
    }
}