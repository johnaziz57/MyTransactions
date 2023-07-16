package com.example.mytransactoins.domain.repo

import com.example.mytransactoins.domain.model.Result
import com.example.mytransactoins.domain.model.User

interface UserRepo {
    fun addUser(email: String, password: String)
    fun getCurrentUser(): User?
    fun logIn(email: String, password: String): Result
    fun logOut()
}