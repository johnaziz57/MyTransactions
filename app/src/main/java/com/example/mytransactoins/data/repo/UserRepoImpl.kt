package com.example.mytransactoins.data.repo

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.mytransactoins.data.util.Hasher
import com.example.mytransactoins.domain.interactor.login.IncorrectCredentialsException
import com.example.mytransactoins.domain.interactor.login.LoginException
import com.example.mytransactoins.domain.interactor.login.UserDoesNotExistException
import com.example.mytransactoins.domain.interactor.register.RegistrationException
import com.example.mytransactoins.domain.model.Result
import com.example.mytransactoins.domain.model.User
import com.example.mytransactoins.domain.repo.UserRepo
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserRepoImpl @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val hasher: Hasher
) : UserRepo {

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

    override fun addUser(email: String, password: String): Result<Unit, RegistrationException> {
        usersPreferences.edit().putString(email, hasher.hash(password)).apply()
        return Result.Success(Unit)
    }

    override fun getCurrentUser(): User? {
        val email = currentUserPreferences.getString(CURRENT_USER, null) ?: return null
        return User(email)
    }

    override fun logIn(email: String, password: String): Result<User, LoginException> {
        val savedHashedPassword = usersPreferences.getString(email, null)
            ?: return Result.Error(UserDoesNotExistException())

        if (hasher.verify(password, savedHashedPassword).not()) {
            return Result.Error(IncorrectCredentialsException())
        }

        currentUserPreferences.edit().putString(CURRENT_USER, email).apply()
        return Result.Success(User(email))
    }

    override fun logOut() {
        currentUserPreferences.edit().putString(CURRENT_USER, null).apply()
    }

    override fun hasUser(email: String): Boolean {
        return usersPreferences.contains(email)
    }

    companion object {
        private const val USERS_PREFERENCES = "USERS_PREFERENCES"
        private const val CURRENT_USER_PREFERENCES = "CURRENT_USER_PREFERENCES"

        private const val CURRENT_USER = "CURRENT_USER"
    }
}