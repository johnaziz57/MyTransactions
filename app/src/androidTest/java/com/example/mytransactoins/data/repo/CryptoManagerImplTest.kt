package com.example.mytransactoins.data.repo

import org.junit.Test


internal class CryptoManagerImplTest {

    @Test
    fun testCryptoManagerEncryptAndDecryptSuccessfully() {
        val cryptoManagerImpl = CryptoManagerImpl()
        val message = "TEST TEST TEST TEST TEST TEST TEST TEST TEST"
        val input = message.toByteArray()
        val encryptedMessage = cryptoManagerImpl.encrypt(input)
        val decryptedMessage = cryptoManagerImpl.decrypt(encryptedMessage)
        assert(input.contentEquals(decryptedMessage))
    }
}