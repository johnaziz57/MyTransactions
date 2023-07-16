package com.example.mytransactoins.domain.interactor.login

import com.example.mytransactoins.domain.interactor.common.ValidateEmailException
import com.example.mytransactoins.domain.model.NewResult
import com.example.mytransactoins.domain.model.User

interface LoginInteractor {
    fun login(email: String, password: String): NewResult<User, LoginException>

    fun logout()
    fun validateEmail(email: String): NewResult<Unit, ValidateEmailException>
    fun validatePasswordLength(password: String): NewResult<Unit, PasswordTooShortException>
}