package com.example.mytransactoins.domain.interactor.login

import com.example.mytransactoins.domain.model.Result
import com.example.mytransactoins.domain.repo.UserRepo
import com.example.mytransactoins.domain.utils.Constants
import com.example.mytransactoins.domain.utils.Utils
import javax.inject.Inject

class LoginInteractorImpl @Inject constructor(
    private val userRepo: UserRepo
) : LoginInteractor {

    override fun validateEmail(email: String): Result {
        return Utils.validateEmail(email)
    }

    override fun validatePasswordLength(password: String): Result {
        if (password.length < Constants.PASSWORD_LENGTH) {
            return Result(false, "Password is too short")
        }
        return Result(true)
    }


    override fun login(email: String, password: String): Result {
        return userRepo.logIn(email, password)
    }

    override fun logout() {
        userRepo.logOut()
    }
}