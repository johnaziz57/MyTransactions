package com.example.mytransactoins.domain.interactor.transaction

import com.example.mytransactoins.domain.repo.TranscationRepo
import javax.inject.Inject

class TransactionInteractorImpl @Inject constructor(
    private val transactionRepo: TranscationRepo
) : TransactionInteractor {
    override fun getTransactions(): List<String> {
        return transactionRepo.getTranscations()
    }
}