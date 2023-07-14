package com.example.mytransactoins.domain.interactor.login

import com.example.mytransactoins.domain.model.Result

class LoginInteractorImpl : LoginInteractor {
    override fun login(email: String, password: String): Result {
        return Result(false, "Username or password not correct")
    }
}