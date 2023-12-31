package com.example.mytransactoins.domain.interactor.transaction

import com.example.mytransactoins.domain.model.Result
import com.example.mytransactoins.domain.repo.TransactionRepo
import javax.inject.Inject

class TransactionInteractorImpl @Inject constructor(
    private val transactionRepo: TransactionRepo
) : TransactionInteractor {
    override fun getTransactions(): Result<List<String>, TransactionException> {
        return transactionRepo.getTransactions()
    }
}