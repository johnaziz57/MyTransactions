package com.example.mytransactoins.domain.interactor.register.email

import com.example.mytransactoins.domain.model.Result

interface RegistrationEmailInteractor {
    fun validateEmail(email: String): Result<Unit, RegistrationEmailException>
}