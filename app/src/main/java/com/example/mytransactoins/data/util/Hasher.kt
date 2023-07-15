package com.example.mytransactoins.data.util

import com.lambdapioneer.argon2kt.Argon2Kt
import com.lambdapioneer.argon2kt.Argon2Mode
import java.security.SecureRandom
import javax.inject.Inject

class Hasher @Inject constructor() {

    private val argon2Kt = Argon2Kt()
    private val secureRandom = SecureRandom()

    fun hash(password: String): String {
        val salt = ByteArray(16)
        secureRandom.nextBytes(salt)
        return argon2Kt.hash(
            mode = Argon2Mode.ARGON2_I,
            password = password.toByteArray(DEFAULT_CHARSET),
            salt = salt,
        ).encodedOutputAsString()
    }

    fun verify(value: String, hash: String): Boolean {
        return argon2Kt.verify(
            mode = Argon2Mode.ARGON2_I,
            encoded = hash,
            password = value.toByteArray(DEFAULT_CHARSET)
        )

    }

    companion object {
        private val DEFAULT_CHARSET = Charsets.UTF_8
    }
}