package com.example.mytransactoins.domain.interactor.register.password_validation

import com.example.mytransactoins.domain.model.NewResult

interface ValidateRegisterPasswordInteractor {
    fun validatePassword(
        password: String,
        repeatedPassword: String
    ): NewResult<Unit, PasswordValidationException>
}