package com.example.mytransactoins.domain.interactor.register

import com.example.mytransactoins.domain.model.Result

interface RegistrationInteractor {
    fun registerUser(email: String, password: String): Result
}