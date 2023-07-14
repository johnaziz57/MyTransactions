package com.example.mytransactoins.data.repo

import com.example.mytransactoins.domain.repo.TranscationRepo
import javax.inject.Inject

class TransactionRepoImpl @Inject constructor() : TranscationRepo {
    override fun getTranscations(): List<String> {
        return listOf("Transaction 1", "Transaction 2", "Transaction 3")
    }
}