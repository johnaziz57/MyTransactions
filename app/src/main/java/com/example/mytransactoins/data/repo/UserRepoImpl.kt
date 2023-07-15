package com.example.mytransactoins.data.repo

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.mytransactoins.domain.model.User
import com.example.mytransactoins.domain.repo.UserRepo
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserRepoImpl @Inject constructor(
    @ApplicationContext
    private val context: Context
) : UserRepo {
    // TODO add Resource class to contain the result and error if something goes wrong
    private val usersPreferences: SharedPreferences
    private val currentUserPreferences: SharedPreferences

    init {
        val masterKey = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        usersPreferences = EncryptedSharedPreferences.create(
            USERS_PREFERENCES,
            masterKey,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
        currentUserPreferences = EncryptedSharedPreferences.create(
            CURRENT_USER_PREFERENCES,
            masterKey,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    override fun getUser(email: String): User? {
        val password = usersPreferences.getString(email, null) ?: return null
        return User(email, password)
    }

    override fun addUser(email: String, password: String) {
        usersPreferences.edit().putString(email, password).apply()
    }

    override fun isLoggedIn(): Boolean {
        return currentUserPreferences.getBoolean(IS_LOGGED_IN_KEY, false)
    }

    override fun logIn(email: String, password: String) {
        currentUserPreferences.edit().putBoolean(IS_LOGGED_IN_KEY, true).apply()
    }

    override fun logOut() {
        currentUserPreferences.edit().putBoolean(IS_LOGGED_IN_KEY, false).apply()
    }

    companion object {
        private const val USERS_PREFERENCES = "USERS_PREFERENCES"
        private const val CURRENT_USER_PREFERENCES = "CURRENT_USER_PREFERENCES"

        private const val IS_LOGGED_IN_KEY = "IS_LOGGED_IN"
    }
}