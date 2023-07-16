package com.example.mytransactoins.domain.interactor.register.password_validation

import com.example.mytransactoins.domain.model.NewResult
import com.example.mytransactoins.domain.utils.Constants
import javax.inject.Inject

class ValidateRegisterPasswordInteractorImpl @Inject constructor() :
    ValidateRegisterPasswordInteractor {
    override fun validatePassword(
        password: String,
        repeatedPassword: String
    ): NewResult<Unit, PasswordValidationException> {
        if (password.length < Constants.PASSWORD_LENGTH) {
            return NewResult.Error(PasswordIsTooShortException())
        }
        if (password != repeatedPassword) {
            return NewResult.Error(PasswordsDoNotMatchException())
        }

        val containsDigitAndLetters =
            password.any { it.isDigit() } && password.any { it.isLetter() }
        if (!containsDigitAndLetters) {
            return NewResult.Error(PasswordDoesNotHaveLettersAndDigits())
        }
        return NewResult.Success(Unit)
    }
}