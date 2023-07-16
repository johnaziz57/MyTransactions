package com.example.mytransactoins.domain.repo

import com.example.mytransactoins.domain.interactor.login.LoginException
import com.example.mytransactoins.domain.interactor.register.RegistrationException
import com.example.mytransactoins.domain.model.Result
import com.example.mytransactoins.domain.model.User

interface UserRepo {
    fun addUser(email: String, password: String): Result<Unit, RegistrationException>
    fun getCurrentUser(): User?
    fun logIn(email: String, password: String): Result<User, LoginException>
    fun logOut()
    fun hasUser(email: String): Boolean
}