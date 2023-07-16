package com.example.mytransactoins.domain.interactor.register

import com.example.mytransactoins.domain.model.Result
import com.example.mytransactoins.domain.utils.Constants
import javax.inject.Inject

class ValidateRegisterPasswordInteractorImpl @Inject constructor() :
    ValidateRegisterPasswordInteractor {
    override fun validatePassword(password: String, repeatedPassword: String): Result {
        if (password.length < Constants.PASSWORD_LENGTH) {
            return Result(isSuccessful = false, message = "Password is too short")
        }
        if (password != repeatedPassword) {
            return Result(isSuccessful = false, message = "Password don't match")
        }

        val containsDigitAndLetters =
            password.any { it.isDigit() } && password.any { it.isLetter() }
        if (!containsDigitAndLetters) {
            return Result(
                isSuccessful = false,
                message = "Password doesn't contain digits and letters"
            )
        }
        return Result(isSuccessful = true)
    }
}