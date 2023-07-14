package com.example.mytransactoins.domain.interactor.transaction

interface TransactionInteractor {
    fun getTransactions(): List<String>
}