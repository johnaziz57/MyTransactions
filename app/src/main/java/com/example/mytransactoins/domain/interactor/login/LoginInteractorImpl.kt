package com.example.mytransactoins.domain.interactor.login

import com.example.mytransactoins.domain.model.Result
import com.example.mytransactoins.domain.model.User
import com.example.mytransactoins.domain.repo.UserRepo
import javax.inject.Inject

class LoginInteractorImpl @Inject constructor(
    private val userRepo: UserRepo
) : LoginInteractor {
    private var user: User? = null

    override val isLoggedIn: Boolean
        get() = user != null

    init {
        user = userRepo.getCurrentUser()?.let { User(it) }
    }

    override fun login(email: String, password: String): Result {
        val result = userRepo.logIn(email, password)
        user = User(email)
        return result
    }

    override fun logout() {
        userRepo.logOut()
        user = null
    }
}