package com.example.mytransactoins.domain.interactor.register

import com.example.mytransactoins.domain.model.NewResult

interface RegistrationInteractor {
    fun registerUser(email: String, password: String): NewResult<Unit, RegistrationException>

}