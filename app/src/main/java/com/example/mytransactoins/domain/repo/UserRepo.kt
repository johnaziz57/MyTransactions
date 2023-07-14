package com.example.mytransactoins.domain.repo

interface UserRepo {
    fun getPassword(email: String): String
    fun addUser(email: String, password: String)
}