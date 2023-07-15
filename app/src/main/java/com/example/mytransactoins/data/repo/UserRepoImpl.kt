package com.example.mytransactoins.data.repo

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.mytransactoins.data.util.Hasher
import com.example.mytransactoins.domain.model.Result
import com.example.mytransactoins.domain.repo.UserRepo
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserRepoImpl @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val hasher: Hasher
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

    override fun addUser(email: String, password: String) {
        usersPreferences.edit().putString(email, hasher.hash(password)).apply()
    }

    // TODO change return type to User
    override fun getCurrentUser(): String? {
        return currentUserPreferences.getString(CURRENT_USER, null)
    }

    // TODO change return type to Result<User>
    override fun logIn(email: String, password: String): Result {
        val savedHashedPassword = usersPreferences.getString(email, null)
            ?: return Result(false, "User doesn't exists")

        if (hasher.verify(password, savedHashedPassword).not()) {
            return Result(false, "Password is incorrect")
        }

        currentUserPreferences.edit().putString(CURRENT_USER, email).apply()
        return Result(true)
    }

    override fun logOut() {
        currentUserPreferences.edit().putString(CURRENT_USER, null).apply()
    }

    companion object {
        private const val USERS_PREFERENCES = "USERS_PREFERENCES"
        private const val CURRENT_USER_PREFERENCES = "CURRENT_USER_PREFERENCES"

        private const val CURRENT_USER = "CURRENT_USER"
    }
}