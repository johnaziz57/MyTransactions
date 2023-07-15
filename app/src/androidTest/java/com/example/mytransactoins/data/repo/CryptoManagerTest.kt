package com.example.mytransactoins.data.repo

import org.junit.Test


internal class CryptoManagerTest {

    @Test
    fun testCryptoManagerEncryptAndDecryptSuccessfully() {
        fun testEncryptionDecryption(message: String) {
            val cryptoManager = CryptoManager()
            val input = message.toByteArray()
            val encryptedMessage = cryptoManager.encrypt(input)
            val decryptedMessage = cryptoManager.decrypt(encryptedMessage)
            assert(input.contentEquals(decryptedMessage))
        }

        listOf("1", "", "TEST", "TEST TEST TEST TEST TEST TEST").forEach {
            testEncryptionDecryption(it)
        }
    }
}