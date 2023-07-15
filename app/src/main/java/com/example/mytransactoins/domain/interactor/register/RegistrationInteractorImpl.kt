package com.example.mytransactoins.domain.interactor.register

import com.example.mytransactoins.domain.model.Result
import com.example.mytransactoins.domain.repo.CryptoManager
import com.example.mytransactoins.domain.repo.UserRepo
import javax.inject.Inject

class RegistrationInteractorImpl @Inject constructor(
    private val userRepo: UserRepo,
    private val cryptoManager: CryptoManager
) : RegistrationInteractor {
    override fun registerUser(email: String, password: String): Result {
        val encryptedPassword = cryptoManager.encrypt(password.toByteArray()).toString()
        userRepo.addUser(email, encryptedPassword)
        userRepo.logIn(email, encryptedPassword)
        return Result(true)
    }
}