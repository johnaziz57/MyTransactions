package com.example.mytransactoins.domain.repo

import com.example.mytransactoins.domain.interactor.transaction.TransactionException
import com.example.mytransactoins.domain.model.Result

interface TransactionRepo {
    fun getTransactions(): Result<List<String>, TransactionException>
}