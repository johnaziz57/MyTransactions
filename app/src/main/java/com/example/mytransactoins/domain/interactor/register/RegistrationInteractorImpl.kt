package com.example.mytransactoins.domain.interactor.register

import com.example.mytransactoins.domain.model.Result
import com.example.mytransactoins.domain.repo.UserRepo
import javax.inject.Inject

class RegistrationInteractorImpl @Inject constructor(
    private val userRepo: UserRepo
) : RegistrationInteractor {
    override fun registerUser(
        email: String,
        password: String
    ): Result<Unit, RegistrationException> {
        return userRepo.addUser(email, password)
    }
}