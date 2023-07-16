package com.example.mytransactoins.domain.repo

import com.example.mytransactoins.domain.interactor.login.LoginException
import com.example.mytransactoins.domain.model.NewResult
import com.example.mytransactoins.domain.model.User

interface UserRepo {
    fun addUser(email: String, password: String)
    fun getCurrentUser(): User?
    fun logIn(email: String, password: String): NewResult<User, LoginException>
    fun logOut()
}