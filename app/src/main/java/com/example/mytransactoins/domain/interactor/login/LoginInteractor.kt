package com.example.mytransactoins.domain.interactor.login

import com.example.mytransactoins.domain.model.Result

interface LoginInteractor {

    val isLoggedIn: Boolean

    fun login(email: String, password: String): Result

    fun logout()
}