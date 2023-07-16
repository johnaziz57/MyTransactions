package com.example.mytransactoins.domain.repo

import com.example.mytransactoins.domain.interactor.transaction.TransactionException
import com.example.mytransactoins.domain.model.NewResult

interface TransactionRepo {
    fun getTransactions(): NewResult<List<String>, TransactionException>
}