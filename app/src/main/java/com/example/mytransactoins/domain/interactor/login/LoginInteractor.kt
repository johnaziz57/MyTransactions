package com.example.mytransactoins.domain.interactor.login

import com.example.mytransactoins.domain.interactor.common.ValidateEmailException
import com.example.mytransactoins.domain.model.Result
import com.example.mytransactoins.domain.model.User

interface LoginInteractor {
    fun login(email: String, password: String): Result<User, LoginException>
    fun logout()
    fun validateEmail(email: String): Result<Unit, ValidateEmailException>
    fun validatePasswordLength(password: String): Result<Unit, PasswordTooShortException>
}