package com.example.mytransactoins.domain.repo

import com.example.mytransactoins.domain.model.User

interface UserRepo {
    fun getUser(email: String): User?
    fun addUser(email: String, password: String)
    fun isLoggedIn(): Boolean
    fun logIn(email: String, password: String)
    fun logOut()
}