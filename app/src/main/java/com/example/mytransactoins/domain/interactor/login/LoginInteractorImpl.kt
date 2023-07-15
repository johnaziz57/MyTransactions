package com.example.mytransactoins.domain.interactor.login

import com.example.mytransactoins.domain.model.User
import com.example.mytransactoins.domain.repo.CryptoManager
import com.example.mytransactoins.domain.repo.UserRepo
import javax.inject.Inject

class LoginInteractorImpl @Inject constructor(
    private val userRepo: UserRepo
) : LoginInteractor {
    private var user: User? = null

    override val isLoggedIn: Boolean
        get() = user != null

    override fun login(username: String, password: String): Result<User> {
        TODO("Not yet implemented")
    }

    override fun logout() {
        TODO("Not yet implemented")
    }
}