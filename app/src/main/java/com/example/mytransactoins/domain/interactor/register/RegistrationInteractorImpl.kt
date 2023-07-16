package com.example.mytransactoins.domain.interactor.register

import com.example.mytransactoins.domain.model.NewResult
import com.example.mytransactoins.domain.repo.UserRepo
import javax.inject.Inject

class RegistrationInteractorImpl @Inject constructor(
    private val userRepo: UserRepo
) : RegistrationInteractor {
    override fun registerUser(
        email: String,
        password: String
    ): NewResult<Unit, RegistrationException> {
        return userRepo.addUser(email, password)
    }
}