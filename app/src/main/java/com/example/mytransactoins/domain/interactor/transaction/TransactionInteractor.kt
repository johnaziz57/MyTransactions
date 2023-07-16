package com.example.mytransactoins.domain.interactor.transaction

import com.example.mytransactoins.domain.model.NewResult

interface TransactionInteractor {
    fun getTransactions(): NewResult<List<String>, TransactionException>
}