package com.example.mytransactoins.data.repo

import com.example.mytransactoins.domain.repo.TranscationRepo

class TranscationRepoImpl : TranscationRepo {
    override fun getTranscations(): List<String> {
        return listOf("Transaction 1", "Transaction 2", "Transaction 3")
    }
}