package com.example.mytransactoins.data.util

import at.favre.lib.crypto.bcrypt.BCrypt
import javax.inject.Inject

class Hasher @Inject constructor() {
    fun hash(value: String): String {
        return BCrypt.with(BCrypt.Version.VERSION_2Y).hash(
            BCRYPT_COST, value.toByteArray(DEFAULT_CHARSET)
        ).toString(DEFAULT_CHARSET)
    }

    fun verify(value: String, hash: String): Boolean {
        return BCrypt.verifyer()
            .verify(
                value.toByteArray(DEFAULT_CHARSET),
                hash.toByteArray(DEFAULT_CHARSET)
            ).verified
    }

    companion object {
        private val DEFAULT_CHARSET = Charsets.UTF_8

        // TODO find the best cost for BCrypt
        private const val BCRYPT_COST = 10
    }
}