package com.example.mytransactoins.domain.interactor.login

import com.example.mytransactoins.domain.interactor.common.ValidateEmailException
import com.example.mytransactoins.domain.interactor.common.ValidateEmailInteractor
import com.example.mytransactoins.domain.model.Result
import com.example.mytransactoins.domain.model.User
import com.example.mytransactoins.domain.repo.UserRepo
import com.example.mytransactoins.domain.utils.Constants
import javax.inject.Inject

class LoginInteractorImpl @Inject constructor(
    private val userRepo: UserRepo,
    private val validateEmailInteractor: ValidateEmailInteractor
) : LoginInteractor {

    override fun validateEmail(email: String): Result<Unit, ValidateEmailException> {
        return validateEmailInteractor.validateEmail(email)
    }

    override fun validatePasswordLength(password: String): Result<Unit, PasswordTooShortException> {
        if (password.length < Constants.PASSWORD_LENGTH) {
            return Result.Error(PasswordTooShortException())
        }
        return Result.Success(Unit)
    }


    override fun login(email: String, password: String): Result<User, LoginException> {
        return userRepo.logIn(email, password)
    }

    override fun logout() {
        userRepo.logOut()
    }
}