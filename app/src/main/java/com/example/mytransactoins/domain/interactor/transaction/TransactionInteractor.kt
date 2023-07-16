package com.example.mytransactoins.domain.interactor.transaction

import com.example.mytransactoins.domain.model.Result

interface TransactionInteractor {
    fun getTransactions(): Result<List<String>, TransactionException>
}