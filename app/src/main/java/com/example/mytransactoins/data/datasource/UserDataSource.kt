package com.example.mytransactoins.data.datasource

import com.example.mytransactoins.data.model.EncryptedUserDTO

interface UserDataSource {
    fun getUser(): EncryptedUserDTO
    fun addUser(encryptedUserDTO: EncryptedUserDTO)
}