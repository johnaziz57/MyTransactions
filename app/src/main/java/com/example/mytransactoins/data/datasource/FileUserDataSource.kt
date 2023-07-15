package com.example.mytransactoins.data.datasource

import com.example.mytransactoins.data.model.EncryptedUserDTO
import javax.inject.Inject

class FileUserDataSource @Inject constructor() : UserDataSource {
    override fun getUser(): EncryptedUserDTO {
        TODO("Implement writing to file")
    }

    override fun addUser(encryptedUserDTO: EncryptedUserDTO) {

    }
}