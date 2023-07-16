package com.example.mytransactoins.domain.interactor.login

import com.example.mytransactoins.domain.interactor.common.ValidateEmailException
import com.example.mytransactoins.domain.interactor.common.ValidateEmailInteractor
import com.example.mytransactoins.domain.model.NewResult
import com.example.mytransactoins.domain.model.User
import com.example.mytransactoins.domain.repo.UserRepo
import com.example.mytransactoins.domain.utils.Constants
import javax.inject.Inject

class LoginInteractorImpl @Inject constructor(
    private val userRepo: UserRepo,
    private val validateEmailInteractor: ValidateEmailInteractor
) : LoginInteractor {

    override fun validateEmail(email: String): NewResult<Unit, ValidateEmailException> {
        return validateEmailInteractor.validateEmail(email)
    }

    override fun validatePasswordLength(password: String): NewResult<Unit, PasswordTooShortException> {
        if (password.length < Constants.PASSWORD_LENGTH) {
            return NewResult.Error(PasswordTooShortException())
        }
        return NewResult.Success(Unit)
    }


    override fun login(email: String, password: String): NewResult<User, LoginException> {
        return userRepo.logIn(email, password)
    }

    override fun logout() {
        userRepo.logOut()
    }
}