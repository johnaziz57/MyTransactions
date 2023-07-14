package com.example.mytransactoins.domain.repo

interface UserRepo {
    fun getEncryptedPassword(email: String): String
    fun addUser(email: String, password: String)
}