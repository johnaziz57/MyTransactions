package com.example.mytransactoins.data.repo

import com.example.mytransactoins.data.datasource.UserDataSource
import com.example.mytransactoins.domain.repo.UserRepo
import javax.inject.Inject

class UserRepoImpl @Inject constructor(private val userDataSource: UserDataSource) : UserRepo {
    // TODO add Resource class to contain the result and error if something goes wrong
    override fun getEncryptedPassword(email: String): String {
        return ""// TODO get data from DB
    }

    override fun addUser(email: String, encryptedPassword: String) {
        //TODO save user to DB
    }
}