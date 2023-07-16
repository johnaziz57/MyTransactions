package com.example.mytransactoins.domain.repo

interface TransactionRepo {
    fun getTransactions(): List<String>
}