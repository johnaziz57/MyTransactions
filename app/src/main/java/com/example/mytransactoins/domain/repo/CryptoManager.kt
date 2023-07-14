package com.example.mytransactoins.domain.repo

interface CryptoManager {
    fun encrypt(bytes: ByteArray): ByteArray
    fun decrypt(bytes: ByteArray): ByteArray
}