package com.example.mytransactoins.domain.interactor.login

import com.example.mytransactoins.domain.model.User

interface LoginInteractor {

    val isLoggedIn: Boolean

    fun login(username: String, password: String): Result<User>

    fun logout()
}