package com.example.mytransactoins.domain.interactor.register

import com.example.mytransactoins.domain.model.Result

interface ValidateRegisterPasswordInteractor {
    fun validatePassword(password: String, repeatedPassword: String): Result
}