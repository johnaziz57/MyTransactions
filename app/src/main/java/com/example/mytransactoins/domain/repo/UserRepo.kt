package com.example.mytransactoins.domain.repo

import com.example.mytransactoins.domain.model.Result

interface UserRepo {
    fun addUser(email: String, password: String)
    fun getCurrentUser(): String?
    fun logIn(email: String, password: String): Result
    fun logOut()
}