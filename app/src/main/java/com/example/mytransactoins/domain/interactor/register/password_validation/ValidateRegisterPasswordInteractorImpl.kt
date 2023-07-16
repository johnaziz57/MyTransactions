package com.example.mytransactoins.domain.interactor.register.password_validation

import com.example.mytransactoins.domain.model.Result
import com.example.mytransactoins.domain.utils.Constants
import javax.inject.Inject

class ValidateRegisterPasswordInteractorImpl @Inject constructor() :
    ValidateRegisterPasswordInteractor {
    override fun validatePassword(
        password: String,
        repeatedPassword: String
    ): Result<Unit, PasswordValidationException> {
        if (password.length < Constants.PASSWORD_LENGTH) {
            return Result.Error(PasswordIsTooShortException())
        }
        if (password != repeatedPassword) {
            return Result.Error(PasswordsDoNotMatchException())
        }

        val containsDigitAndLetters =
            password.any { it.isDigit() } && password.any { it.isLetter() }
        if (!containsDigitAndLetters) {
            return Result.Error(PasswordDoesNotHaveLettersAndDigits())
        }
        return Result.Success(Unit)
    }
}