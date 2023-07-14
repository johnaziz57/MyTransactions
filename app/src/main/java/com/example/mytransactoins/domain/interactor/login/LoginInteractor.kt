package com.example.mytransactoins.domain.interactor.login

import com.example.mytransactoins.domain.model.Result

interface LoginInteractor {
    fun login(email: String, password: String): Result
}