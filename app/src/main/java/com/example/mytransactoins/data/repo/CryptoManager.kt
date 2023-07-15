package com.example.mytransactoins.data.repo

import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.inject.Inject

class CryptoManager @Inject constructor() {
    // KeyStore.getInstance gets the indicated keystore out of the list of available keystores
    // Since this is Android the available keystore is `AndroidKeySore`
    private val keyStore = KeyStore.getInstance("AndroidKeystore").apply {
        load(null)
    }

    fun encrypt(bytes: ByteArray): ByteArray {
        // TODO check best practices for either creating a cipher every time or reuse the old one
        val encryptCipher = getEncryptCipher()
        val encryptedData = encryptCipher.doFinal(bytes)
        return encryptCipher.iv + encryptedData
    }

    fun decrypt(bytes: ByteArray): ByteArray {
        val iv = bytes.sliceArray(0 until IV_SIZE)
        return getDecryptCipher(iv).doFinal(bytes.sliceArray(IV_SIZE until bytes.size))
    }

    /**
     * Gets already saved key or creates a new one
     */
    private fun getKey(): SecretKey {
        val existingKey = keyStore.getEntry(KEY_NAME, null) as? KeyStore.SecretKeyEntry
        return existingKey?.secretKey ?: createKey()
    }

    private fun createKey(): SecretKey {
        return KeyGenerator.getInstance(ALGORITHM).apply {
            val builder = KeyGenParameterSpec.Builder(
                KEY_NAME,
                KeyProperties.PURPOSE_DECRYPT or KeyProperties.PURPOSE_ENCRYPT
            )
            with(builder) {
                // BlockMode is to determines how the blocks interact with one another when
                // encrypted/decrypted. BlockMode means that it the encryption/decryption will
                // always operates on units or "blocks" that are some fixed number of bytes in size.
                setBlockModes(BLOCK_MODE)
                // padding is any of a number of distinct practices which all include adding data to
                // the beginning, middle, or end of a message prior to encryption
                setEncryptionPaddings(PADDING)
                setUserAuthenticationRequired(false)
                setRandomizedEncryptionRequired(true)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    // https://developer.android.com/training/articles/keystore#HardwareSecurityModule
                    // Supported devices running API 28 or higher can have Hardware-backed keystore
                    setIsStrongBoxBacked(true)
                }

            }
            val keyGenParameterSpec = builder.build()

            init(keyGenParameterSpec)
        }.generateKey()
    }

    private fun getEncryptCipher(): Cipher {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, getKey())
        return cipher
    }

    /**
     * @param initializationVector initialization vector (IV) is an arbitrary number that can be
     * used with a secret key for data encryption to foil cyber attacks. The use of an IV prevents
     * the repetition of a sequence of text in data encryption.
     */
    private fun getDecryptCipher(initializationVector: ByteArray): Cipher {
        return Cipher.getInstance(TRANSFORMATION).apply {
            init(Cipher.DECRYPT_MODE, getKey(), IvParameterSpec(initializationVector))
        }
    }

    companion object {
        private const val KEY_NAME = "SECRET"

        // https://www.javamex.com/tutorials/cryptography/aes_block_ciphers.shtml
        // AES is a block Cypher. AES works on block of size 16 bytes
        private const val ALGORITHM = KeyProperties.KEY_ALGORITHM_AES

        // Before encrypting a block, we XOR the data with the encrypted bytes of the previous
        // block.
        private const val BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC

        // Assuming block size is 8 bytes, PKCS7 adds padding equal to the number of missing bytes
        // in the block. i.e. if Block equals `DD DD DD DD` and size is 8, it is missing 4 bytes.
        // PKCS7 will turn it into `DD DD DD DD 04 04 04 04`.
        private const val PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
        private const val TRANSFORMATION = "$ALGORITHM/$BLOCK_MODE/$PADDING"

        private const val IV_SIZE = 16
    }
}