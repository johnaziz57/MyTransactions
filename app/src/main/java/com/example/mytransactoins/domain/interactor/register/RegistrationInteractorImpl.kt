package com.example.mytransactoins.domain.interactor.register

import com.example.mytransactoins.domain.model.Result

class RegistrationInteractorImpl : RegistrationInteractor {
    override fun registerUser(email: String, password: String): Result {
        //TODO encrypt the password and save it
        return Result(true)
    }
}