package com.example.mytransactoins.domain.repo

interface TranscationRepo {
    fun getTranscations(): List<String>
}