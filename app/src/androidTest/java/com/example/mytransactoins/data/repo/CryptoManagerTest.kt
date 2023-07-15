package com.example.mytransactoins.data.repo

import org.junit.Test


internal class CryptoManagerTest {

    @Test
    fun testCryptoManagerEncryptAndDecryptSuccessfully() {
        fun testEncryptionDecryption(message: String) {
            val cryptoManagerImpl = CryptoManagerImpl()
            val input = message.toByteArray()
            val encryptedMessage = cryptoManagerImpl.encrypt(input)
            val decryptedMessage = cryptoManagerImpl.decrypt(encryptedMessage)
            assert(input.contentEquals(decryptedMessage))
        }

        listOf("1", "", "TEST", "TEST TEST TEST TEST TEST TEST").forEach {
            testEncryptionDecryption(it)
        }
    }
}