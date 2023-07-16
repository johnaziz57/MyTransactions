package com.example.mytransactoins.domain.interactor.login

import com.example.mytransactoins.domain.model.Result

interface LoginInteractor {
    fun login(email: String, password: String): Result

    fun logout()
    fun validateEmail(email: String): Result
    fun validatePasswordLength(password: String): Result
}