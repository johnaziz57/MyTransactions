package com.example.mytransactoins.domain.interactor.transaction

import com.example.mytransactoins.domain.repo.TransactionRepo
import javax.inject.Inject

class TransactionInteractorImpl @Inject constructor(
    private val transactionRepo: TransactionRepo
) : TransactionInteractor {
    override fun getTransactions(): List<String> {
        return transactionRepo.getTransactions()
    }
}